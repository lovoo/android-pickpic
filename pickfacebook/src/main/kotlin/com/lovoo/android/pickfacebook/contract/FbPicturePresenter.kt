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