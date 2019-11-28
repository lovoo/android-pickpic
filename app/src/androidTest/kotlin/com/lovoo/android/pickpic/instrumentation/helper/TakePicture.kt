package com.lovoo.android.pickpic.instrumentation.helper

import android.content.Context
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

object TakePicture {

    private val cameras = listOf(GoogleCam(), EmuCam())

    fun capture(device: UiDevice, context: Context) {
        val camera = cameras.find { it.packaName == AppStart.getCameraPackageName(context) }
        camera?.takePicture(device)
    }

    private class GoogleCam : Camera {
        override val packaName: String
            get() = "com.google.android.GoogleCamera"

        override fun takePicture(device: UiDevice) {
            val shutterId = "shutter_button"
            val retakeId = "retake_button"

            device.apply {
                wait(Until.hasObject(By.res(packaName, shutterId)), Consts.WAIT_DELAY)
                findObject(By.res(packaName, shutterId)).click()

                wait(Until.hasObject(By.res(packaName, retakeId)), Consts.WAIT_DELAY)
                findObject(By.res(packaName, shutterId)).click()
            }
        }

    }

    private class EmuCam : Camera {
        override val packaName: String
            get() = "com.android.camera2"

        override fun takePicture(device: UiDevice) {
            val shutterId = "shutter_button"
            val doneId = "done_button"

            device.apply {
                wait(Until.hasObject(By.pkg(packaName)), Consts.WAIT_DELAY)

                wait(Until.hasObject(By.res(packaName, shutterId)), Consts.WAIT_DELAY)

                findObject(By.res(packaName, shutterId)).click()

                wait(Until.hasObject(By.res(packaName, doneId)), Consts.WAIT_DELAY)

                findObject(By.res(packaName, doneId)).click()
            }
        }

    }

    private interface Camera {
        val packaName: String
        fun takePicture(device: UiDevice)
    }
}