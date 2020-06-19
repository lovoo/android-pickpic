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
package com.lovoo.android.pickcam.view

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lovoo.android.pickcam.R
import com.lovoo.android.pickcam.worker.CaptureResultWorker
import com.lovoo.android.pickcore.contract.CameraDestination
import com.lovoo.android.pickcore.contract.CaptureCallback
import com.lovoo.android.pickcore.destination.PrivateDirectory
import com.lovoo.android.pickcore.destination.PublicDirectory
import com.lovoo.android.pickcore.loader.CameraLoader
import com.lovoo.android.pickcore.permission.Permission

/**
 * Ready to use solution to handle Android Camera capture.
 *
 * Use PickPicCaptureFragment.start() and let your [Activity]
 * or parent [Fragment] implement [CaptureCallback].
 *
 * It is based on [DialogFragment] and will be visible
 * shortly as an overlay.
 *
 * Modify pickapp proguard rules as well when package is changed or class is renamed!!!
 */
class PickPicCaptureFragment : DialogFragment() {

    private var captureDestination: CameraDestination? = null
    private var captureCallback: CaptureCallback? = null

    private val onWorkerDoneReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            captureCallback?.onCapture(CaptureResultWorker.getUri(intent))
            dismissAllowingStateLoss()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pickpic_capture_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        captureCallback = if (context is CaptureCallback) {
            context
        } else {
            val parentFragment = parentFragment
            if (parentFragment is CaptureCallback) {
                parentFragment
            } else {
                throw (IllegalArgumentException("You have to implement CaptureCallback"))
            }
        }
        context.registerReceiver(onWorkerDoneReceiver, IntentFilter().apply { addAction(CaptureResultWorker.INTENT_ACTION_ON_RESULT) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            captureDestination = it.getParcelable(BUNDLE_CAPTURE_DESTINATION) as? CameraDestination?
        } ?: kotlin.run { startCamera() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(BUNDLE_CAPTURE_DESTINATION, captureDestination)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != CAMERA_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }

        CameraLoader.stopCamera(requireContext(), captureDestination)

        val destination = captureDestination
        if (resultCode == Activity.RESULT_OK && destination != null) {
            CaptureResultWorker.start(destination, WORKER_NAME)
        } else {
            captureCallback?.onCapture(null)
            dismissAllowingStateLoss()
        }
    }

    override fun onDetach() {
        context?.unregisterReceiver(onWorkerDoneReceiver)
        super.onDetach()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val deniedPermissions = Permission.getDeniedPermissions(requireActivity(), Permission.cameraPermissions.plus(Permission.galleryPermissions))
            if (deniedPermissions.isEmpty()) {
                startCamera()
            } else {
                captureCallback?.onCapture(null)
                dismissAllowingStateLoss()
            }
        }
    }

    private fun startCamera() {
        val deniedList = Permission.getDeniedPermissions(requireActivity(), Permission.cameraPermissions.plus(Permission.galleryPermissions))
        if (deniedList.isNotEmpty()) {
            val preferences = requireContext().getSharedPreferences(PREF_CATEGORY, Context.MODE_PRIVATE)
            val isFirstTime = preferences.getBoolean(PREF_KEY_PERMISSION, true)
            if (!isFirstTime && deniedList.firstOrNull { it.second } != null) {
                Permission.openSettings(requireActivity())
            } else {
                preferences.edit().putBoolean(PREF_KEY_PERMISSION, false).apply()
                val permissions = Array(deniedList.size) { i -> deniedList[i].first }
                Permission.requestPermissions(this, PERMISSION_REQUEST_CODE, permissions)
            }
            return
        }

        captureDestination = when (Build.VERSION.SDK_INT < 24) {
            true -> PublicDirectory()
            else -> {
                val directory = ContextCompat.getExternalFilesDirs(requireContext(), Environment.DIRECTORY_PICTURES)
                    .firstOrNull()
                when (directory) {
                    null -> PublicDirectory() // need proper error handle
                    else -> {
                        val packageName = context?.applicationInfo?.packageName
                            ?: requireContext().packageName
                        PrivateDirectory("$packageName.pickpicfileprovider", directory)
                    }
                }
            }
        }.also {
            CameraLoader.startCamera(requireContext(), this, CAMERA_REQUEST_CODE, it)
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 2734
        private const val PERMISSION_REQUEST_CODE = 2735
        private const val BUNDLE_CAPTURE_DESTINATION = "pickpic_capture_destination"
        private const val PREF_CATEGORY = "pickpic_storage"
        private const val PREF_KEY_PERMISSION = "permission_first_time"
        private const val WORKER_NAME = "PickPic Capture Worker"

        private fun newInstance() = PickPicCaptureFragment()

        /**
         * @param fragmentManager the fragment manager that should add [PickPicCaptureFragment]
         * @param tag the identifier for this [Fragment]
         */
        fun start(fragmentManager: FragmentManager, tag: String) {
            newInstance().apply {
                show(fragmentManager, tag)
            }
        }
    }
}
