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
package com.lovoo.android.pickcore.engine

import android.graphics.Bitmap
import com.lovoo.android.pickcore.contract.PictureEvaluatorEngine

class DisabledPictureEvaluatorEngine : PictureEvaluatorEngine<Unit> {

    override fun categorizePicture(bitmap: Bitmap, callback: (Unit) -> Unit) {
    }

    override fun rateCategory(item: Unit) = 1f
}
