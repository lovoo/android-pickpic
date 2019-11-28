package com.lovoo.android.pickcore.engine

import com.lovoo.android.pickcore.contract.CameraEngine
import com.lovoo.android.pickcore.model.GalleryLib

/**
 * Default implementation of [CameraEngine] that disables PickPic camera support.
 *
 * isEnabled returns always false and
 * startCamera executes nothing
 *
 * @ee [com.lovoo.android.pickcore.loader.PictureLoader]
 */
class DisabledCameraEngine : CameraEngine {
    override fun isEnabled(gallery: GalleryLib) = false
    override fun getDisplayName() = "Camera"
    override fun startCamera() {
        // no-op
    }
}