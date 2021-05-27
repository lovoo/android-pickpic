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
package com.lovoo.android.pickml.evaluator

import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetector
import com.lovoo.android.pickcore.contract.PictureEvaluatorEngine
import com.lovoo.android.pickml.model.ProfilePictureData

class ProfilePictureEvaluator(
    private val faceDetector: FaceDetector
) : PictureEvaluatorEngine<ProfilePictureData> {

    override fun categorizePicture(bitmap: Bitmap, callback: (ProfilePictureData) -> Unit) {
        val scale = (MAX_IMAGE_SIZE / bitmap.width).coerceAtMost(MAX_IMAGE_SIZE / bitmap.height)
        // avoid out of memory in firebase -> scale bitmap to < 1000px
        val safeBitmap =
            when (scale < 1f) {
                true -> Bitmap.createScaledBitmap(bitmap, (bitmap.width * scale).toInt(), (bitmap.height * scale).toInt(), true)
                false -> bitmap.copy(Bitmap.Config.RGB_565, true)
            }
        if (safeBitmap === bitmap) {
            callback.invoke(ProfilePictureData())
            return
        }
        val image = InputImage.fromBitmap(safeBitmap, 0)
        val imageArea = safeBitmap.width * safeBitmap.height
        faceDetector.process(image).addOnSuccessListener {
            val faces = it.map { face ->
                val rect = Rect(
                    face.boundingBox.left,
                    face.boundingBox.top,
                    face.boundingBox.right,
                    face.boundingBox.bottom
                )
                Log.d("mktest", "$face")
                val faceArea = rect.width() * rect.height()
                FaceData(faceArea / imageArea.toFloat(), face.smilingProbability ?: 0f)
            }.sortedBy { it.area }

            val face = faces.lastOrNull()
            callback.invoke(
                ProfilePictureData(
                    numberOfFaces = faces.size,
                    faceSizeRatio = face?.area ?: 0f,
                    smileFactor = face?.smile ?: 0f
                )
            )
        }
    }

    override fun rateCategory(item: ProfilePictureData): Float {
        if (item.numberOfFaces == 0) return 0f
        var rate = RATE_MIDDLE
        rate *= if (item.numberOfFaces == 1) RATE_UP else RATE_DOWN
        // rate *= if (item.isOutDoor) RATE_UP else RATE_DOWN
        rate *= if (item.faceSizeRatio > 0.09f && item.faceSizeRatio < 0.5f) RATE_UP else RATE_DOWN
        rate *= item.smileFactor + RATE_MIDDLE
        return rate
    }

    data class FaceData(
        val area: Float,
        val smile: Float
    )

    companion object {
        private const val RATE_MIDDLE = 0.5f
        private const val RATE_DOWN = 0.5f
        private const val RATE_UP = 1.5f
        private const val MAX_IMAGE_SIZE = 600f
    }
}
