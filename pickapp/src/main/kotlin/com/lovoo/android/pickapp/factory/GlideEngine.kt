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
package com.lovoo.android.pickapp.factory

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
     * @param corner the rounded corner in pixel
     * @param errorRes the optional resource as error fallback
     */
    override fun loadThumbnail(context: Context, size: Int, uri: Uri, target: ImageView, corner: Int, errorRes: Int) {
        // forward the request to the other method
        loadImage(context, size, size, uri, target, corner, errorRes)
    }

    /**
     * Load images from [Uri] with centerCrop and fixed width and height.
     *
     * @param context the app context
     * @param width the requested image width, must be greater then 0
     * @param height the requested image height, must be greater then 0
     * @param uri the requested local or remote [Uri] to the picture
     * @param target the [ImageView] that should receive the Bitmap
     * @param corner the rounded corner in pixel
     * @param errorRes the optional resource as error fallback
     */
    override fun loadImage(context: Context, width: Int, height: Int, uri: Uri, target: ImageView, corner: Int, @DrawableRes errorRes: Int) {
        // skip load call when context is finished activity
        if (context is Activity && (context.isFinishing || context.isDestroyed)) {
            return
        }

        // load the image for the requested size and handle error case
        Glide.with(context)
            .load(uri)
            .apply(
                RequestOptions()
                    .override(width, height)
                    .also {
                        if (corner > 0) {
                            it.transform(CenterCrop(), RoundedCorners(corner))
                        } else {
                            it.centerCrop()
                        }
                    },
            )
            .apply {
                if (errorRes != 0) {
                    error(errorRes)
                }
            }
            .into(target)
    }
}
