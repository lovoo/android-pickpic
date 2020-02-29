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
package com.lovoo.android.pickcore.permission

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * A small solution for Android Permission handle.
 */
object Permission {

    /**
     * List of permissions that are needed to load images from the device.
     */
    @SuppressLint("InlinedApi")
    val galleryPermissions = when (isM()) {
        true -> arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        false -> emptyArray()
    }

    /**
     * List of permission that are needed to capture images from the camera.
     */
    val cameraPermissions = when (isM()) {
        true -> arrayOf(Manifest.permission.CAMERA)
        false -> emptyArray()
    }

    /**
     * Validate if all permissions are granted (result empty).
     *
     * @param activity current activity
     * @param permissions list of requested permissions
     * @return list of denied permissions and if they are denied always
     */
    fun getDeniedPermissions(activity: Activity, permissions: Array<String>): List<Pair<String, Boolean>> {
        if (!isM() || permissions.isEmpty()) return emptyList()

        val result = mutableListOf<Pair<String, Boolean>>()
        permissions.forEach {
            // Camera Permission is only needed if added to the Manifest
            if (it == Manifest.permission.CAMERA && !isCameraManifestPermission(activity)) {
                return@forEach // like continue
            }
            if (ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED) {
                result.add(it to !activity.shouldShowRequestPermissionRationale(it))
            }
        }

        return result
    }

    /**
     * Request a set of permissions.
     *
     * @param fragment that should receive the result
     * @param requestCode the code to identify the result
     * @param permissions the requested permissions
     */
    fun requestPermissions(fragment: Fragment, requestCode: Int, permissions: Array<String>) {
        if (!isM() || permissions.isEmpty()) return

        fragment.requestPermissions(permissions, requestCode)
    }

    /**
     * Request a set of permissions.
     *
     * @param activity that should receive the result
     * @param requestCode the code to identify the result
     * @param permissions the requested permissions
     */
    fun requestPermissions(activity: Activity, requestCode: Int, permissions: Array<String>) {
        if (!isM() || permissions.isEmpty()) return

        activity.requestPermissions(permissions, requestCode)
    }

    /**
     * Navigate to the App setting. To enable permissions that were denied always.
     * @param activity some activity
     */
    fun openSettings(activity: Activity) {
        Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", activity.packageName, null)
        }.let {
            activity.startActivity(it)
        }
    }

    private fun isM() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    // check if the Manifest declares to use the camera permission
    private fun isCameraManifestPermission(context: Context) = context.packageManager
            .getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
            .requestedPermissions.contains(Manifest.permission.CAMERA)
}
