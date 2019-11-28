package com.lovoo.android.pickapp.factory

/**
 * Define the optional Feature configuration from PickPic based on the available dependencies.
 */
class PickDependencies {

    /**
     * Is true if FbPictureFragment is packed within the dependencies.
     */
    val hasFacebook: Boolean by lazy {
        try {
            Class.forName("com.lovoo.android.pickfacebook.view.FbPictureFragment")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }

    /**
     * Is true if PickPicCaptureFragment is packed within the dependencies.
     */
    val hasCamera: Boolean by lazy {
        try {
            Class.forName("com.lovoo.android.pickcam.view.PickPicCaptureFragment")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }
}