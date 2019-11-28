package com.lovoo.android.pickcore.loader

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.exifinterface.media.ExifInterface
import androidx.fragment.app.Fragment
import com.lovoo.android.pickcore.contract.CameraDestination
import com.lovoo.android.pickcore.contract.getUri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

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
                scale = Math.max(bitmapWidth, bitmapHeight) / maxSize.toFloat()
            }

            val degree = ExifInterface(filePath).rotationDegrees

            val shouldScale = scale < 0.9f
            val shouldRotate = degree % 360 != 0
            if (!shouldScale && !shouldRotate) {
                updateMediaScanner(context, arrayOf(filePath), listener)
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

            // delete original
            File(filePath).delete()

            // create new file
            val file = File(filePath.replace(".jpg", "-2.jpg"))

            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)

            } finally {
                if (fos != null) {
                    fos.flush()
                    fos.close()
                }
                updateMediaScanner(context, arrayOf(filePath, file.absolutePath), listener)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun updateMediaScanner(context: Context, files: Array<String>, listener: MediaScannerConnection.OnScanCompletedListener) {
        MediaScannerConnection.scanFile(context, files, null) { path, uri ->
            listener.onScanCompleted(path, uri)
            context.sendBroadcast(Intent(INTENT_INVALIDATE_GALLERY))
            if (uri != null) {
                context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
            } else {
                context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(path)))
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