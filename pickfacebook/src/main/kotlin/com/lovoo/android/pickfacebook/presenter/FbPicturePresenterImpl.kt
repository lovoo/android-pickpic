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
package com.lovoo.android.pickfacebook.presenter

import android.content.Intent
import com.facebook.GraphRequest
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickfacebook.Facebook
import com.lovoo.android.pickfacebook.contract.FbPicturePresenter
import com.lovoo.android.pickfacebook.contract.FbPictureView

/**
 * Implementation of [FbPicturePresenter] to load the pictures from Facebook.
 * Don't forget to forward onDestroy().
 *
 * Start loading with swap().
 *
 * @param view the contract to the UI Layer
 */
class FbPicturePresenterImpl(val view: FbPictureView) : FbPicturePresenter {

    private var facebook = Facebook()

    /**
     * Load pictures from a Facebook [Gallery] from start.
     *
     * @param gallery the selected [Gallery]
     */
    override fun swap(gallery: Gallery) {
        next(gallery, null)
    }

    /**
     * Load pictures from a Facebook [Gallery] from [GraphRequest].
     *
     * @param gallery the selected [Gallery]
     * @param next the request to fetch the next page or null to load from start
     */
    override fun next(gallery: Gallery, next: GraphRequest?) {
        facebook.fetchPictures(next, gallery.id) { data, error, id, nextRequest ->
            if (error != null) {
                view.onError(error)
            }
            view.onPicturesLoaded(data, id, nextRequest)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return facebook.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        facebook.destroy()
    }
}
