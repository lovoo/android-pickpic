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

import com.lovoo.android.pickcore.contract.CameraEngine
import com.lovoo.android.pickcore.model.GalleryLib

/**
 * Default implementation of [CameraEngine] that disables PickPic camera support.
 *
 * isEnabled returns always false and
 * startCamera executes nothing
 *
 * @ee [com.lovoo.android.pickcore.loader.PictureLoader]
 */
class DisabledCameraEngine : CameraEngine {
    override fun isEnabled(gallery: GalleryLib) = false
    override fun getDisplayName() = "Camera"
    override fun startCamera() {
        // no-op
    }
}
