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
package com.lovoo.android.pickui.view

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
 * Custom Drawable that is used as translucent placeholder.
 * In comparison to other Drawables this one can get
 * width and height to measure its dimensions.
 *
 * It draws nothing therefor aplha channel and color filter are ignored.
 */
class PlaceHolderDrawable : Drawable() {

    var width: Int = 0
    var height: Int = 0

    override fun getIntrinsicWidth() = width

    override fun getIntrinsicHeight() = height

    override fun draw(canvas: Canvas?) {
        // no-op
    }

    override fun setAlpha(alpha: Int) {
        // no-op
    }

    override fun getOpacity() = PixelFormat.OPAQUE

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // no-op
    }
}
