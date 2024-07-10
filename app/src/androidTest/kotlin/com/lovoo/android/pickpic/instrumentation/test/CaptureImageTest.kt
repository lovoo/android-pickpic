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
package com.lovoo.android.pickpic.instrumentation.test

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import com.lovoo.android.pickpic.R
import com.lovoo.android.pickpic.instrumentation.helper.AppStart
import com.lovoo.android.pickpic.instrumentation.helper.BaseUiTest
import com.lovoo.android.pickpic.instrumentation.helper.Consts
import com.lovoo.android.pickpic.instrumentation.helper.RecyclerViewMatcher
import com.lovoo.android.pickpic.instrumentation.helper.TakePicture
import org.junit.Test

class CaptureImageTest : BaseUiTest() {

    @Test
    fun verifyPickPicStarts() {
        AppStart.launchApp(device)
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext

        onView(withId(R.id.button)).perform(click())
        takePictures(2, context)

        device.wait(Until.hasObject(By.res(context.resources.getResourceName(R.id.picture_list_view))), Consts.WAIT_DELAY)
        onView(withId(R.id.picture_list_view)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.picture_list_view)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

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
