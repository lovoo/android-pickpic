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

/**
 * Define the optional Feature configuration from PickPic based on the available dependencies.
 */
class PickDependencies {

    /**
     * Is true if FbPictureFragment is packed within the dependencies.
     */
    val hasFacebook: Boolean by lazy {
        try {
            Class.forName("com.lovoo.android.pickfacebook.view.FbPictureFragment")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }

    /**
     * Is true if PickPicCaptureFragment is packed within the dependencies.
     */
    val hasCamera: Boolean by lazy {
        try {
            Class.forName("com.lovoo.android.pickcam.view.PickPicCaptureFragment")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }
}
