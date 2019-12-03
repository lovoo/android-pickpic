package com.lovoo.android.pickcore.contract

import android.net.Uri


/**
 * The interface that the calling UI should implement to receive the captured file.
 */
interface CaptureCallback {
    /**
     * @param uri the path to the captured file or null
     */
    fun onCapture(uri: Uri?)
}