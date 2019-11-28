package com.lovoo.android.pickcore

import com.lovoo.android.pickcore.contract.CameraEngine
import com.lovoo.android.pickcore.contract.ImageEngine
import com.lovoo.android.pickcore.contract.SelectionHolder
import com.lovoo.android.pickcore.engine.DefaultImageEngine
import com.lovoo.android.pickcore.engine.DisabledCameraEngine

/**
 * Object that allows at runtime to change core feature from PickPic.
 * If you use [com.lovoo.android.pickapp.view.PickPicActivity] you don`t need to provide anything on your own.
 *
 * Please watch out for memory leaks if you provide instances that hold a reference
 * to any UI element such as Activity, Fragment, View...
 *
 * Example:
 *
 * class MyActivity: Activity, ImageEngine, CameraEngine {
 *
 *   override fun onResume() {
 *      super.onResume()
 *      PickPicProvider.imageEngine = this
 *      PickPicProvider.cameraEngine = this
 *      ...
 *   }
 *
 *   override fun onPause() {
 *      super.onPause()
 *      PickPicProvider.imageEngine = DefaultImageEngine() // remove Activity reference
 *      PickPicProvider.cameraEngine = DisabledCameraEngine() // remove Activity reference
 *      ...
 *   }
 *
 *   ...
 * }
 *
 * As you can see in the example the reference will be cleared in onDestroy.
 * But I want to encourage you to use a stand-alone Engine class without any UI reference.
 * And even if you do so please consider the issue with singleton instance and Lifecycle.
 * For example an Activity set an engine in onCreate and clears it in onDestroy, when two
 * such Activities are created then the engine from Activity A will be overwritten by Activity B.
 * Or worse Activity B will clear the engine in onDestroy and Activity A is just resumed.
 *
 * Best would be to use [com.lovoo.android.pickapp.view.PickPicActivity] as singleInstance
 * or set engine within your Application.onCreate() call.
 */
object PickPicProvider {
    /**
     * [ImageEngine] that is used from PickPic to load images as thumbnails and preview.
     * Should be set at soon as possible
     */
    var imageEngine: ImageEngine = DefaultImageEngine()

    /**
     * [CameraEngine] that defines if the camera will be available for a certain [Gallery].
     * Please consider to keep the user flow consistent. Its a bad experience when the user
     * navigate to a custom folder, used the camera button there and the taken picture is added
     * to the DCIM or another folder. It would be better to show the camera button only in
     * Galleries were you can save the picture, so that it appears afterwards.
     */
    var cameraEngine: CameraEngine = DisabledCameraEngine()

    /**
     * [SelectionHolder] implementation that keeps track of the selected [Picture]s and provides
     * the option to listen for changes.
     */
    var selectionHolder: SelectionHolder? = null
}
