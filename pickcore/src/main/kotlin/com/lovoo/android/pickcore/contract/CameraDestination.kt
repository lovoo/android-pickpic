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
package com.lovoo.android.pickcore.contract

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.os.Parcelable
import androidx.core.content.FileProvider
import androidx.core.os.EnvironmentCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Model class that represents a destination for captured images.
 *
 * It contains the authority for Android N and higher, the target directory and the file
 * within this directory.
 */
interface CameraDestination : Parcelable {
    val authority: String
    val directory: File
    val file: File?
}

/**
 * Resolves the destination to a valid [Uri].
 * If authority is set then the [Uri] will be resolved as content uri otherwise as absolute file [Uri].
 *
 * @param context app context
 * @return the [Uri] for this file if possible
 */
fun CameraDestination.getUri(context: Context): Uri? = when {
    authority.isEmpty() -> file?.let { Uri.fromFile(it) }
    else -> file?.let { FileProvider.getUriForFile(context, authority, it) }
}

/**
 * Stores a file in the directory of this [CameraDestination].
 * @param fileName optional complete filename or if not set JPEG_<date>.jpg
 */
fun CameraDestination.createTempFile(fileName: String = ""): File? {

    if (Environment.MEDIA_MOUNTED != EnvironmentCompat.getStorageState(directory)) {
        // the storage state is broken
        return null
    }

    directory.mkdirs()

    if (!directory.canWrite()) {
        // we can not create a new file within this directory
        return null
    }

    val name = when (fileName.isEmpty()) {
        true -> "JPEG_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())}.jpg"
        else -> fileName
    }
    return File(directory, name)
}
