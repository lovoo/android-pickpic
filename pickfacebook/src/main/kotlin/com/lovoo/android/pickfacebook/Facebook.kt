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
package com.lovoo.android.pickfacebook

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.Profile
import com.facebook.ProfileTracker
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickfacebook.model.Converter
import com.lovoo.android.pickfacebook.model.FbPicture

/**
 * Implementation of the Facebook SDK.
 */
class Facebook {

    private val callbackManager = CallbackManager.Factory.create()
    private val accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            accessToken.postValue(currentAccessToken?.token)
        }
    }

    private val profileTracker = object : ProfileTracker() {
        override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
            if (currentProfile == null) {
                user.postValue(null)
            } else {
                user.postValue(
                    User(
                        currentProfile.id,
                        currentProfile.name,
                        currentProfile.getProfilePictureUri(1000, 1000).path ?: ""
                    )
                )
            }
        }
    }

    /**
     * Observable to receive new access token from Facebook or null.
     */
    val accessToken = MutableLiveData<String?>()

    /**
     * Observable to receive new [User] from Facebook
     */
    val user = MutableLiveData<User?>()

    init {
        accessTokenTracker.startTracking()
        profileTracker.startTracking()
    }

    /**
     * Start Login process and request access token.
     *
     * @param fragment the [Fragment] that should receive the onActivityResult call
     * @param permissions list of permissions to request from Facebook
     * @param callback the callback to handle the result
     */
    fun requestAccessToken(
        fragment: Fragment,
        permissions: List<String> = listOf("public_profile"),
        callback: FacebookCallback<LoginResult>?
    ) {

        LoginManager.getInstance().apply {
            registerCallback(callbackManager, callback)
        }.logInWithReadPermissions(fragment, permissions)
    }

    /**
     * Fetch [User] from Facebook.
     */
    fun fetchCurrentProfile() {
        Profile.fetchProfileForCurrentAccessToken()
    }

    /**
     * Fetch <all> [Gallery]s from Facebook. Multiple requests will be executed
     * until the whole list is loaded.
     *
     * @param callback the callback to receive the complete list or error
     */
    fun fetchGalleries(callback: (List<Gallery>, error: Throwable?) -> Unit) {
        val fetchedGalleries = mutableListOf<Gallery>()
        val pagingCallback = object : Callback<List<Gallery>> {
            override fun invoke(data: List<Gallery>, error: Throwable?, next: GraphRequest?) {
                fetchedGalleries.addAll(data)

                if (error == null && next != null) {
                    fetchGalleries(next, this)
                } else {
                    callback.invoke(fetchedGalleries, error)
                }
            }
        }

        fetchGalleries(null, pagingCallback)
    }

    private fun fetchGalleries(next: GraphRequest?, callback: Callback<List<Gallery>>) {
        val responseCallback = { response: GraphResponse ->
            val json = response.jsonObject
            val nextRequest = response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT)

            callback.invoke(Converter.convertToGalleries(json), Converter.convert(response.error?.exception), nextRequest)
        }

        val request = next ?: GraphRequest(AccessToken.getCurrentAccessToken(), "/me/albums").apply {
            parameters = Bundle().apply {
                putString("fields", "id,count,name,picture")
            }
        }

        request.apply { setCallback { responseCallback.invoke(it) } }.executeAsync()
    }

    /**
     * Fetch [FbPicture]s from Facebook.
     *
     * @param next the [GraphRequest] for the next page or null for init request
     * @param albumId the Identifier of the requested album
     * @param callback the callback to receive the loaded list or error together with the request for the next page
     */
    fun fetchPictures(next: GraphRequest?, albumId: String, callback: (List<FbPicture>, error: Throwable?, albumId: String, next: GraphRequest?) -> Unit) {
        val responseCallback = { response: GraphResponse ->
            val json = response.jsonObject
            val nextRequest = response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT)

            callback.invoke(Converter.convertToPictures(json), Converter.convert(response.error?.exception), albumId, nextRequest)
        }

        val request = next ?: GraphRequest(AccessToken.getCurrentAccessToken(), "/$albumId/photos").apply {
            parameters = Bundle().apply {
                putString("fields", "id,images")
            }
        }

        request.apply { setCallback { responseCallback.invoke(it) } }.executeAsync()
    }

    /**
     * Logout from Facebook.
     */
    fun clearSession() {
        if (!isLoggedIn()) return
        LoginManager.getInstance().logOut()
    }

    /**
     * @return true if [AccessToken] is set
     */
    fun isLoggedIn() = AccessToken.getCurrentAccessToken() != null

    /**
     * @return true if [AccessToken] is expired or not set
     */
    fun isExpired() = AccessToken.getCurrentAccessToken()?.isExpired ?: true

    /**
     * @return [Set] of permissions that are granted or null
     */
    fun getPermissions(): Set<String>? = AccessToken.getCurrentAccessToken()?.permissions

    /**
     * @return [Set] of declined permission or null
     */
    fun getDeclinedPermissions(): Set<String>? = AccessToken.getCurrentAccessToken()?.declinedPermissions

    /**
     * Forward the data to Facebook SDK.
     *
     * @return true if result was handled by Facebook
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Cleanup listener.
     */
    fun destroy() {
        accessTokenTracker.stopTracking()
        profileTracker.stopTracking()
    }

    private interface Callback<in T> {
        fun invoke(data: T, error: Throwable?, next: GraphRequest?)
    }

    /**
     * @param id the identifier
     * @param name the user name
     * @param picture the url to the avatar
     */
    data class User(
        val id: String,
        val name: String,
        val picture: String
    )
}
