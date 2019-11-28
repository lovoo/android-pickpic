package com.lovoo.android.pickpic.instrumentation

import androidx.annotation.CallSuper
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.lovoo.android.pickpic.instrumentation.helper.AppStart
import org.junit.Before
import org.junit.Ignore

@Ignore abstract class BaseUiTest {

    protected lateinit var device: UiDevice

    @Before
    @CallSuper
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        AppStart.grantPermission()
    }
}