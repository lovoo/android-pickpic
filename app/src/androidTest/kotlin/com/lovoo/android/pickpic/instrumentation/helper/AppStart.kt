package com.lovoo.android.pickpic.instrumentation.helper

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

object AppStart {

    private val launcherPackage: String
        get() {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            val pm = InstrumentationRegistry.getInstrumentation().context.packageManager
            val resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
            return resolveInfo.activityInfo.packageName
        }

    fun launchApp(device: UiDevice) {
        device.apply {
            pressHome()
            wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), Consts.WAIT_DELAY)
        }
        val context = InstrumentationRegistry.getInstrumentation().context
        val packageName = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext.packageName
        val intent = context.packageManager
                .getLaunchIntentForPackage(packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), Consts.WAIT_DELAY)
    }


    fun grantPermission() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        InstrumentationRegistry.getInstrumentation().uiAutomation.grantRuntimePermission(context.packageName, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        InstrumentationRegistry.getInstrumentation().uiAutomation.grantRuntimePermission(context.packageName, android.Manifest.permission.READ_EXTERNAL_STORAGE)

        //permissions for google camera
        val cameraPackage = getCameraPackageName(context)
        InstrumentationRegistry.getInstrumentation().uiAutomation.grantRuntimePermission(cameraPackage, android.Manifest.permission.ACCESS_FINE_LOCATION)
        InstrumentationRegistry.getInstrumentation().uiAutomation.grantRuntimePermission(cameraPackage, android.Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    fun getCameraPackageName(context: Context): String {
        val packageManager = context.packageManager
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        return packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).first().activityInfo.packageName
    }
}