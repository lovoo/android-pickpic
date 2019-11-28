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