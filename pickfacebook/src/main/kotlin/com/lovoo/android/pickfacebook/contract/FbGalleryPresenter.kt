package com.lovoo.android.pickfacebook.contract

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Contract to implement for Presenter.
 *
 * @see com.lovoo.android.pickfacebook.presenter.FbGalleryPresenterImpl
 */
interface FbGalleryPresenter {

    fun onAttach(activity: FragmentActivity)
    fun onCreate(savedInstanceState: Bundle?)
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
    fun onDestroy()

    /**
     * @return true when a valid Facebook Session exists
     */
    fun isLoggedIn(): Boolean

    /**
     * @return true when active session has picture permission granted
     */
    fun isPicturePermissionGranted(): Boolean

    /**
     * Start login process or update session to request picture permission.
     *
     * @param fragment the Fragment that executes this request (for ActivityResult)
     */
    fun loginWithPicturePermission(fragment: Fragment): Any

    /**
     * Request the Facebook galleries. Please remember to check for permissions beforehand.
     */
    fun loadGalleries()

    /**
     * Called when an error occurs. Presenter should clean up.
     *
     * @param error the [Throwable] that occurred
     */
    fun handleError(error: Throwable?)
}