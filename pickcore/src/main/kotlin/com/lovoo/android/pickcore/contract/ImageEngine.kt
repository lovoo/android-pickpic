package com.lovoo.android.pickcore.contract

import android.content.Context
import android.net.Uri
import android.widget.ImageView


/**
 * Engine interface responsible for setting up the PickPic image loading.
 *
 * @see com.lovoo.android.pickcore.engine.DefaultImageEngine
 * @see com.lovoo.android.pickapp.factory.GlideEngine
 */
interface ImageEngine {
    /**
     * Load a small image.
     *
     * @param context app [Context]
     * @param size the requested width and height of the image
     * @param uri the image [Uri]
     * @param target the image view that show the bitmap
     * @param errorRes the error drawable if request failed or 0
     */
    fun loadThumbnail(context: Context, size: Int, uri: Uri, target: ImageView, errorRes: Int = 0)

    /**
     * Load an image.
     *
     * @param context app [Context]
     * @param width the requested width of the image
     * @param height the requested height of the image
     * @param uri the image [Uri]
     * @param target the image view that show the bitmap
     * @param errorRes the error drawable if request failed or 0
     */
    fun loadImage(context: Context, width: Int, height: Int, uri: Uri, target: ImageView, errorRes: Int = 0)
}