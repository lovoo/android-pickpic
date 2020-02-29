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
package com.lovoo.android.pickfacebook.contract

import androidx.lifecycle.LifecycleOwner
import com.facebook.GraphRequest
import com.lovoo.android.pickfacebook.model.FbPicture

/**
 * Contract to implement for UI.
 *
 * @see com.lovoo.android.pickfacebook.view.FbPictureFragment
 */
interface FbPictureView {

    /**
     * @return [LifecycleOwner] of the view for subscription handle
     */
    fun getLifeCycle(): LifecycleOwner

    /**
     * Called when loading process is finished.
     *
     * @param pictures the list of loaded [FbPicture]
     * @param galleryId the parent [com.lovoo.android.pickcore.model.Gallery] id
     * @param next the request to load the next page of pictures or null
     */
    fun onPicturesLoaded(pictures: List<FbPicture>, galleryId: String, next: GraphRequest?)

    /**
     * Called when an error occurs. View should inform user.
     *
     * @param error the [Throwable] that occurred
     */
    fun onError(error: Throwable)
}
