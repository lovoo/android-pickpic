[pickcore](../../index.md) / [com.lovoo.android.pickcore.loader](../index.md) / [CameraLoader](index.md) / [finalizeCapturedImage](./finalize-captured-image.md)

# finalizeCapturedImage

`fun finalizeCapturedImage(context: Context, file: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`?, listener: OnScanCompletedListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/loader/CameraLoader.kt#L104)

Optimize the captured image to zero rotation and max size of 1280px. It will also send the
[INTENT_INVALIDATE_GALLERY](-i-n-t-e-n-t_-i-n-v-a-l-i-d-a-t-e_-g-a-l-l-e-r-y.md) broadcast.

### Parameters

`context` - the app [Context](#)

`file` - the captured picture [CameraDestination](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md)

`listener` - scan listener to notify when task is done and the modified image is available