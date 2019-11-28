package com.lovoo.android.pickapp.factory

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lovoo.android.pickcore.contract.ImageEngine

/**
 * Implementation of [ImageEngine] to use [Glide] within PickPic.
 */
class GlideEngine : ImageEngine {

    /**
     * Load images from [Uri] with centerCrop and fixed square size.
     *
     * @param context the app context
     * @param size the requested image width and height, must be greater then 0
     * @param uri the requested local or remote [Uri] to the picture
     * @param target the [ImageView] that should receive the Bitmap
     * @param errorRes the optional resource as error fallback
     */
    override fun loadThumbnail(context: Context, size: Int, uri: Uri, target: ImageView, errorRes: Int) {
        // forward the request to the other method
        loadImage(context, size, size, uri, target, errorRes)
    }

    /**
     * Load images from [Uri] with centerCrop and fixed width and height.
     *
     * @param context the app context
     * @param width the requested image width, must be greater then 0
     * @param height the requested image height, must be greater then 0
     * @param uri the requested local or remote [Uri] to the picture
     * @param target the [ImageView] that should receive the Bitmap
     * @param errorRes the optional resource as error fallback
     */
    override fun loadImage(context: Context, width: Int, height: Int, uri: Uri, target: ImageView, @DrawableRes errorRes: Int) {
        // skip load call when context is finished activity
        if (context is Activity && (context.isFinishing || context.isDestroyed)) {
            return
        }

        // load the image for the requested size and handle error case
        Glide.with(context)
                .load(uri)
                .apply(RequestOptions()
                        .override(width, height)
                        .centerCrop())
                .apply {
                    if (errorRes != 0) {
                        error(errorRes)
                    }
                }
                .into(target)
    }

}