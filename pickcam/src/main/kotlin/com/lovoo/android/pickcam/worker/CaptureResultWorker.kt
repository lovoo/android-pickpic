/**
 * Copyright 2018 LOVOO GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lovoo.android.pickcam.worker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.RxWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.lovoo.android.pickcore.contract.CameraDestination
import com.lovoo.android.pickcore.destination.PrivateDirectory
import com.lovoo.android.pickcore.destination.moveToPublicDirectory
import com.lovoo.android.pickcore.loader.CameraLoader
import com.lovoo.android.pickcore.util.isMinimumQ
import io.reactivex.Single
import java.io.File
import java.util.UUID

/**
 * Worker that handles all the tasks to finalize the captured image from the camera.
 * When this worker is done it fires a BroadcastIntent with action CaptureResultWorker.INTENT_ACTION_ON_RESULT.
 *
 * Don not use the constructor directly!!!
 *
 * @see CaptureResultWorker.start(CameraDestination, String)
 * @see CaptureResultWorker.getUri(Intent)
 *
 * @param context the app context
 * @param params the params passed to this [RxWorker]
 */
class CaptureResultWorker(
    private val context: Context,
    params: WorkerParameters,
) : RxWorker(context, params) {

    private var inputFile: File = File(params.inputData.getString(INPUT_FILE))
    private var isPublic: Boolean = params.inputData.getBoolean(INPUT_IS_PUBLIC, true)

    override fun createWork(): Single<Result> {
        return Single.create { emitter ->
            val file = if (isPublic || isMinimumQ()) inputFile else inputFile.moveToPublicDirectory().file

            if (file == null) {
                emitter.onError(RuntimeException("Captured file is null"))
                return@create
            }

            CameraLoader.finalizeCapturedImage(
                context,
                file,
                MediaScannerConnection.OnScanCompletedListener { _, uri ->
                    if (uri == null) return@OnScanCompletedListener
                    // LiveData holds to long the old result so we have to forward the result as broadcast
                    emitter.onSuccess(Result.success(Data.Builder().putString(OUTPUT_URI, uri.toString()).build()))
                },
            )
        }
    }

    companion object {
        const val OUTPUT_URI = "pickcam_output_uri"
        private const val INPUT_FILE = "pickcam_input_file"
        private const val INPUT_IS_PUBLIC = "pickcam_input_is_public"

        /**
         * Receive the [Uri] from the [Data] provided by the [Worker]
         * @param data the output data [Data] provided by the [CaptureResultWorker] or null. If equal
         * to [Data.EMPTY] the null will be returned by this function.
         * @return the [Uri] to the "normalized" captured image or null
         */
        fun getUri(data: Data?): Uri? = data?.takeUnless { it != Data.EMPTY }
            ?.getString(OUTPUT_URI)
            ?.let { Uri.parse(it) }

        /**
         * Enqueue the unique [Worker] with REPLACE policy.
         *
         * @param destination the [CameraDestination] that was used to capture the picture
         * @param name optional unique name for the [Worker] (default: CaptureResultWorker)
         *
         * @return the uuid of the created WorkRequest
         */
        fun start(context: Context, destination: CameraDestination, name: String = "CaptureResultWorker"): UUID {
            val data = workDataOf(
                INPUT_IS_PUBLIC to (destination !is PrivateDirectory),
                INPUT_FILE to (destination.file?.path ?: ""),
            )

            val request = OneTimeWorkRequest.Builder(CaptureResultWorker::class.java)
                .setInputData(data)
                .build()

            WorkManager.getInstance(context).enqueueUniqueWork(name, ExistingWorkPolicy.REPLACE, request)
            return request.id
        }
    }
}
