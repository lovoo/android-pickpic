[pickcore](../../index.md) / [com.lovoo.android.pickcore.loader](../index.md) / [CameraLoader](./index.md)

# CameraLoader

`object CameraLoader` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/loader/CameraLoader.kt#L44)

Object class that helps to start and finish a capture [Intent](#) on Android.

### Properties

| Name | Summary |
|---|---|
| [INTENT_INVALIDATE_GALLERY](-i-n-t-e-n-t_-i-n-v-a-l-i-d-a-t-e_-g-a-l-l-e-r-y.md) | `const val INTENT_INVALIDATE_GALLERY: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Broadcast [Intent](#) action that is triggered when a new image is stored within the gallery. |

### Functions

| Name | Summary |
|---|---|
| [finalizeCapturedImage](finalize-captured-image.md) | `fun finalizeCapturedImage(context: Context, file: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`?, listener: OnScanCompletedListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Optimize the captured image to zero rotation and max size of 1280px. It will also send the [INTENT_INVALIDATE_GALLERY](-i-n-t-e-n-t_-i-n-v-a-l-i-d-a-t-e_-g-a-l-l-e-r-y.md) broadcast. |
| [startCamera](start-camera.md) | `fun startCamera(activity: Activity, requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, destination: `[`CameraDestination`](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>`fun startCamera(context: Context, fragment: Fragment, requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, destination: `[`CameraDestination`](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Start a [MediaStore.ACTION_IMAGE_CAPTURE](#) and pass the [CameraDestination](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md) as [MediaStore.EXTRA_OUTPUT](#) parameter. |
| [stopCamera](stop-camera.md) | `fun stopCamera(context: Context, destination: `[`CameraDestination`](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Revoke temporally granted permissions on the target file. |
