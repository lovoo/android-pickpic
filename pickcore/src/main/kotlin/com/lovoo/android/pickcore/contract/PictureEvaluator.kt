package com.lovoo.android.pickcore.contract

import android.graphics.Bitmap

interface PictureEvaluator<T> {
    suspend fun categorizePicture(bitmap: Bitmap): T
    fun rateCategory(item: T): Float
}