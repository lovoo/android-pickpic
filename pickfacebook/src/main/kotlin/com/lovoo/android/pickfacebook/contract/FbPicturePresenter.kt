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

import android.content.Intent
import com.facebook.GraphRequest
import com.lovoo.android.pickcore.model.Gallery

/**
 * Contract to implement for Presenter.
 *
 * @see com.lovoo.android.pickfacebook.presenter.FbPicturePresenterImpl
 */
interface FbPicturePresenter {

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
    fun onDestroy()

    /**
     * Switch to another [Gallery].
     *
     * @param gallery the requested [Gallery]
     */
    fun swap(gallery: Gallery)

    /**
     * Load new pictures from Facebook.
     *
     * @param gallery the requested [Gallery]
     * @param next the [GraphRequest] provided from Facebook for the next page or null for initial request
     */
    fun next(gallery: Gallery, next: GraphRequest?)
}
