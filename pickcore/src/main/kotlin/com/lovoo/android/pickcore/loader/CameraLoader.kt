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
package com.lovoo.android.pickcore.loader

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import androidx.fragment.app.Fragment
import com.lovoo.android.pickcore.contract.CameraDestination
import com.lovoo.android.pickcore.contract.getUri
import com.lovoo.android.pickcore.util.aboveQ
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

/**
 * Object class that helps to start and finish a capture [Intent] on Android.
 */
object CameraLoader {

    /**
     * Broadcast [Intent] action that is triggered when a new image is stored within the gallery.
     */
    const val INTENT_INVALIDATE_GALLERY = "intent_invalidate_gallery"

    /**
     * Start a [MediaStore.ACTION_IMAGE_CAPTURE] [Intent] and pass the [CameraDestination] as [MediaStore.EXTRA_OUTPUT] parameter.
     *
     * @param activity the [Activity] that starts this [Intent]
     * @param requestCode the code for onActivityResult
     * @param destination the requested target for the captured image
     *
     * @return true if intent could be created false if no app can handle [MediaStore.ACTION_IMAGE_CAPTURE]
     */
    fun startCamera(activity: Activity, requestCode: Int, destination: CameraDestination): Boolean {
        val intent = createIntent(activity, destination)
        intent?.let { activity.startActivityForResult(it, requestCode) }
        return intent != null
    }

    /**
     * Start a [MediaStore.ACTION_IMAGE_CAPTURE] [Intent] and pass the [CameraDestination] as [MediaStore.EXTRA_OUTPUT] parameter.
     *
     * @param context the app [Context]
     * @param fragment the [Fragment] that starts this [Intent]
     * @param requestCode the code for onActivityResult
     * @param destination the requested target for the captured image
     *
     * @return true if intent could be created false if no app can handle [MediaStore.ACTION_IMAGE_CAPTURE]
     */
    fun startCamera(context: Context, fragment: Fragment, requestCode: Int, destination: CameraDestination): Boolean {
        val intent = createIntent(context, destination)
        intent?.let { fragment.startActivityForResult(it, requestCode) }
        return intent != null
    }

    /**
     * Revoke temporally granted permissions on the target file.
     *
     * @param context the app [Context]
     * @param destination the [CameraDestination] that was used for startCamera
     */
    fun stopCamera(context: Context, destination: CameraDestination?) {
        if (Build.VERSION.SDK_INT < 21) {
            destination?.getUri(context)?.let {
                context.revokeUriPermission(it, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        }
    }

    /**
     * Optimize the captured image to zero rotation and max size of 1280px. It will also send the
     * [INTENT_INVALIDATE_GALLERY] broadcast.
     *
     * @param context the app [Context]
     * @param file the captured picture [CameraDestination]
     * @param listener scan listener to notify when task is done and the modified image is available
     */
    fun finalizeCapturedImage(context: Context, file: File?, listener: MediaScannerConnection.OnScanCompletedListener) {
        val filePath = file?.absolutePath ?: ""

        if (filePath.isNotEmpty()) {
            normalizeImageIfNeeded(context, filePath, listener)
        } else {
            listener.onScanCompleted(null, null)
            context.sendBroadcast(Intent(INTENT_INVALIDATE_GALLERY))
        }
    }

    private fun normalizeImageIfNeeded(context: Context, filePath: String, listener: MediaScannerConnection.OnScanCompletedListener) {
        try {
            var scale = 1.0f
            val maxSize = 1280

            var bitmap: Bitmap = BitmapFactory.decodeFile(filePath) ?: run {
                MediaScannerConnection.scanFile(context, arrayOf(filePath), null, listener)
                return
            }

            var bitmapWidth = bitmap.width
            var bitmapHeight = bitmap.height

            if (bitmapWidth > maxSize || bitmapHeight > maxSize) {
                scale = bitmapWidth.coerceAtLeast(bitmapHeight) / maxSize.toFloat()
            }

            val degree = ExifInterface(filePath).rotationDegrees

            val shouldScale = scale < 0.9f
            val shouldRotate = degree % 360 != 0
            if (!shouldScale && !shouldRotate && !aboveQ()) {
                updateMediaScanner(context, arrayOf(filePath), null, listener)
                return
            }

            // create matrix for the manipulation
            val matrix = Matrix()
            if (shouldScale) {
                matrix.postScale(scale, scale)
                bitmapWidth *= scale.toInt()
                bitmapHeight *= scale.toInt()
            }

            if (shouldRotate) {
                matrix.postRotate(degree.toFloat())
            }

            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true)

            // Delete original
            File(filePath).delete()

            // Get the app's name
            val appName = context.getString(context.applicationInfo.labelRes)

            // Calculate new relative path for Q and before Q
            val newFilePath = if (aboveQ()) {
                val tempPath = filePath // .replace(".jpg", "-2.jpg")
                val lastItem = tempPath.split("/").last()
                tempPath.replaceAfterLast("/", "$appName/$lastItem")
            } else {
                filePath // .replace(".jpg", "-2.jpg")
            }

            // Create the new file with specified path
            val file = File(newFilePath)

            var fileUri: Uri? = null
            var fos: OutputStream? = null
            try {
                // Separate logic for Q and before Q
                if (aboveQ()) {
                    // Prepare file values for insertion
                    val values = ContentValues().apply {
                        put(MediaStore.Images.Media.DISPLAY_NAME, file.name)
                        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                        put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/$appName")
                    }

                    // Prepare OutputStream for insertion
                    fileUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                    fileUri?.let { fos = context.contentResolver.openOutputStream(it) }
                } else {
                    // Create new FileOutputStream
                    fos = FileOutputStream(file)
                }

                // Insert file into gallery
                fos?.let { bitmap.compress(Bitmap.CompressFormat.JPEG, 85, it) }
            } finally {
                // Clear stream
                fos?.flush()
                fos?.close()

                // Signal new image was added
                updateMediaScanner(context, arrayOf(file.absolutePath), fileUri, listener)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Notify listeners a new image was added
    private fun updateMediaScanner(context: Context, files: Array<String>, fileUri: Uri?, listener: MediaScannerConnection.OnScanCompletedListener) {
        MediaScannerConnection.scanFile(context, files, null) { path, uri ->
            val curUri = uri ?: fileUri ?: Uri.parse(path)
            listener.onScanCompleted(path, curUri)
            context.sendBroadcast(Intent(INTENT_INVALIDATE_GALLERY))
            if (!aboveQ()) {
                context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, curUri))
            }
        }
    }

    private fun createIntent(context: Context, destination: CameraDestination): Intent? {
        val uri = destination.getUri(context)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            if (uri != null) {
                putExtra(MediaStore.EXTRA_OUTPUT, uri)
            }
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            val flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION

            return intent.apply {
                addFlags(flags)

                if (Build.VERSION.SDK_INT < 21 && uri != null) {
                    context.packageManager.queryIntentActivities(this, PackageManager.MATCH_DEFAULT_ONLY)?.forEach {
                        context.grantUriPermission(it.activityInfo.packageName, uri, flags)
                    }
                }
            }
        }

        return null
    }
}
