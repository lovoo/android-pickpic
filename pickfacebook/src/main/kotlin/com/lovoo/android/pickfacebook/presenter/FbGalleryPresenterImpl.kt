package com.lovoo.android.pickfacebook.presenter

import androidx.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.lovoo.android.pickfacebook.Facebook
import com.lovoo.android.pickfacebook.contract.FbGalleryPresenter
import com.lovoo.android.pickfacebook.contract.FbGalleryView

/**
 * Implementation of [FbGalleryPresenter] for Fragments to load Facebook galleries.
 * Don't forget to forward these events:
 *  onAttach()
 *  onCreate()
 *  onDestroy()
 *
 * Start loading with loadGalleries() if you have the permission to access the Images.
 *
 * @param view the contract to the UI Layer
 */
class FbGalleryPresenterImpl(private val view: FbGalleryView) : FbGalleryPresenter {

    private val facebook = Facebook()

    override fun onAttach(activity: FragmentActivity) {
        // no-op
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        facebook.user.observe(view.getLifeCycle(), Observer { view.onUserChanged(it) })
        facebook.accessToken.observe(view.getLifeCycle(), Observer { view.onAccessTokenChanged(it) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return facebook.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        facebook.destroy()
    }

    /**
     * @return true if Facebook AccessToken is valid
     */
    override fun isLoggedIn() = facebook.isLoggedIn() && !facebook.isExpired()

    /**
     * @return true if permission to access pictures on Facebook is granted
     */
    override fun isPicturePermissionGranted(): Boolean {
        return facebook.getPermissions()?.contains(PERMISSION_PHOTOS) == true
    }

    /**
     * Try to load new access token with picture permission from Facebook.
     *
     * @param fragment the [Fragment] that will receive the onActivityResult call
     */
    override fun loginWithPicturePermission(fragment: Fragment) {
        facebook.requestAccessToken(fragment, listOf(PERMISSION_PHOTOS), object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                // no-op
            }

            override fun onCancel() {
                view.onError(Throwable("user canceled"))
            }

            override fun onError(error: FacebookException?) {
                error?.let { view.onError(it) }
            }

        })
    }

    /**
     * Load list of [com.lovoo.android.pickcore.model.Gallery].
     */
    override fun loadGalleries() {
        facebook.fetchGalleries { data, error ->
            when {
                error != null -> view.onError(error)
                data.isEmpty() -> view.onError(Throwable())
                else -> view.onGalleriesLoaded(data)
            }
        }
    }

    /**
     * Clear the Facebook session.
     *
     * @param error the exception is ignored
     */
    override fun handleError(error: Throwable?) {
        facebook.clearSession()
    }

    companion object {
        private const val PERMISSION_PHOTOS = "user_photos"
    }
}