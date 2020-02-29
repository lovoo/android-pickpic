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

import com.lovoo.android.pickcore.model.GalleryLib

/**
 * Engine interface responsible for setting up the PickPic camera.
 */
interface CameraEngine {

    /**
     * Determine if the Camera icon should be visible in this [GalleryLib].
     *
     * @param gallery the current selected [GalleryLib]
     * @return true if icon should be visible, false otherwise
     */
    fun isEnabled(gallery: GalleryLib): Boolean

    /**
     * @return the localized string value for the camera icon
     */
    fun getDisplayName(): String

    /**
     * Opens a camera intent to receive a captured image.
     *
     * If isEnabled return true this should also be able to start a camera intent,
     * if isEnabled returns false this can be empty then.
     */
    fun startCamera()
}
