package com.lovoo.android.pickml.evaluator

import android.graphics.Bitmap
import com.lovoo.android.pickcore.contract.PictureEvaluator
import com.lovoo.android.pickml.model.ProfilePictureData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfilePictureEvaluator(

) : PictureEvaluator<ProfilePictureData> {

    override suspend fun categorizePicture(bitmap: Bitmap): ProfilePictureData {
        withContext(Dispatchers.IO) {
            // prepare image
            withContext(Dispatchers.Main) {
                // analyse image for CPU benefitial
            }
        }
        // setup data based on analysis
        return ProfilePictureData()
    }

    override fun rateCategory(item: ProfilePictureData): Float {
        if (item.numberOfFaces == 0) return 0f
        var rate = RATE_MIDDLE
        rate *= if (item.numberOfFaces == 1) RATE_UP else RATE_DOWN
        rate *= if (item.isOutDoor) RATE_UP else RATE_DOWN
        rate *= item.smileFactor + RATE_MIDDLE

        // TODO
        // 0.5 - 0.5 --> 0.0
        // 1.0 - 0.5 --> 0.5
        // 0.0 - 0.5 --> 0.5
        // rate *= Math.abs(0.5f - item.faceSizeRatio)
        return rate
    }

    companion object {
        private const val RATE_MIDDLE = 0.5f
        private const val RATE_DOWN = 0.5f
        private const val RATE_UP = 1.5f
    }

}