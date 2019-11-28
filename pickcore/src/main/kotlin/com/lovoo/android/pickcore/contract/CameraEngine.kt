package com.lovoo.android.pickcore.contract

import com.lovoo.android.pickcore.model.GalleryLib

/**
 * Engine interface responsible for setting up the PickPic camera.
 */
interface CameraEngine {

    /**
     * Determine if the Camera icon should be visible in this [GalleryLib].
     *
     * @param gallery the current selected [GalleryLib]
     * @return true if icon should be visible, false otherwise
     */
    fun isEnabled(gallery: GalleryLib): Boolean

    /**
     * @return the localized string value for the camera icon
     */
    fun getDisplayName(): String

    /**
     * Opens a camera intent to receive a captured image.
     *
     * If isEnabled return true this should also be able to start a camera intent,
     * if isEnabled returns false this can be empty then.
     */
    fun startCamera()
}