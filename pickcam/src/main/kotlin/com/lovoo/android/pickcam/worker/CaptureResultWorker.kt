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
import io.reactivex.Single
import java.io.File

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
    params: WorkerParameters
) : RxWorker(context, params) {

    private var inputFile: File = File(params.inputData.getString(INPUT_FILE))
    private var isPublic: Boolean = params.inputData.getBoolean(INPUT_IS_PUBLIC, true)

    override fun createWork(): Single<Result> {
        return Single.create<Result> { emitter ->
            val file = if (isPublic) inputFile else inputFile.moveToPublicDirectory().file

            if (file == null) {
                context.sendBroadcast(Intent(INTENT_ACTION_ON_RESULT))
                emitter.onError(RuntimeException("Captured file is null"))
                return@create
            }

            CameraLoader.finalizeCapturedImage(
                context,
                file,
                MediaScannerConnection.OnScanCompletedListener { _, uri ->
                    if (uri == null) return@OnScanCompletedListener
                    // LiveData holds to long the old result so we have to forward the result as broadcast
                    context.sendBroadcast(Intent(INTENT_ACTION_ON_RESULT).putExtra(OUTPUT_URI, uri))
                    emitter.onSuccess(Result.success())
                }
            )
        }
    }

    companion object {
        private const val INPUT_FILE = "pickcam_input_file"
        private const val INPUT_IS_PUBLIC = "pickcam_input_is_public"
        private const val OUTPUT_URI = "pickcam_output_uri"
        const val INTENT_ACTION_ON_RESULT = "pickcam_result_done"

        /**
         * Receive the [Uri] from the [Intent] provided by the [BroadcastReceiver]
         * @param intent the result [Intent] provided by the [BroadcastReceiver] or null
         * @return the [Uri] to the "normalized" captured image or null
         */
        fun getUri(intent: Intent?): Uri? = intent?.getParcelableExtra(OUTPUT_URI)

        /**
         * Enqueue the unique [Worker] with REPLACE policy.
         *
         * @param destination the [CameraDestination] that was used to capture the picture
         * @param name optional unique name for the [Worker] (default: CaptureResultWorker)
         */
        fun start(destination: CameraDestination, name: String = "CaptureResultWorker") {
            val data = workDataOf(
                INPUT_IS_PUBLIC to (destination !is PrivateDirectory),
                INPUT_FILE to (destination.file?.path ?: "")
            )

            val request = OneTimeWorkRequest.Builder(CaptureResultWorker::class.java)
                .setInputData(data)
                .build()

            WorkManager.getInstance().enqueueUniqueWork(name, ExistingWorkPolicy.REPLACE, request)
        }
    }
}
