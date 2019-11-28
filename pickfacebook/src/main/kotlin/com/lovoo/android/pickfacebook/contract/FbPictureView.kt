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