package com.lovoo.android.pickpic.instrumentation.test

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import com.lovoo.android.pickpic.R
import com.lovoo.android.pickpic.instrumentation.helper.*
import org.junit.Test

class CaptureImageTest : BaseUiTest() {

    @Test
    fun verifyPickPicStarts() {
        AppStart.launchApp(device)
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext

        onView(withId(R.id.button)).perform(click())
        takePictures(2, context)

        device.wait(Until.hasObject(By.res(context.resources.getResourceName(R.id.selection_button))), Consts.WAIT_DELAY)
        onView(withId(R.id.selection_button)).perform(click())

        device.wait(Until.hasObject(By.res(context.resources.getResourceName(R.id.result_list))), Consts.WAIT_DELAY)
        onView(withId(R.id.result_list))
                .check(RecyclerViewMatcher.RecyclerViewItemCountAssertion(2))
    }

    private fun takePictures(amount: Int, context: Context) {
        for (i in 0 until amount) {
            device.apply {
                wait(Until.hasObject(By.text(context.resources.getString(R.string.pickpic_camera_button))), Consts.WAIT_DELAY)
                findObject(By.text(context.resources.getString(R.string.pickpic_camera_button))).click()
            }

            TakePicture.capture(device, context)
        }
    }
}