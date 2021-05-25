[pickcore](../../index.md) / [com.lovoo.android.pickcore.loader](../index.md) / [CameraLoader](index.md) / [startCamera](./start-camera.md)

# startCamera

`fun startCamera(activity: Activity, requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, destination: `[`CameraDestination`](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/loader/CameraLoader.kt#L60)

Start a [MediaStore.ACTION_IMAGE_CAPTURE](#) and pass the [CameraDestination](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md) as [MediaStore.EXTRA_OUTPUT](#) parameter.

### Parameters

`activity` - the [Activity](#) that starts this [Intent](#)

`requestCode` - the code for onActivityResult

`destination` - the requested target for the captured image

**Return**
true if intent could be created false if no app can handle [MediaStore.ACTION_IMAGE_CAPTURE](#)

`fun startCamera(context: Context, fragment: Fragment, requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, destination: `[`CameraDestination`](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/loader/CameraLoader.kt#L76)

Start a [MediaStore.ACTION_IMAGE_CAPTURE](#) and pass the [CameraDestination](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md) as [MediaStore.EXTRA_OUTPUT](#) parameter.

### Parameters

`context` - the app [Context](#)

`fragment` - the [Fragment](#) that starts this [Intent](#)

`requestCode` - the code for onActivityResult

`destination` - the requested target for the captured image

**Return**
true if intent could be created false if no app can handle [MediaStore.ACTION_IMAGE_CAPTURE](#)

