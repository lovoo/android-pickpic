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
