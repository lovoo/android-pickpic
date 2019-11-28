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