[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [CameraEngine](index.md) / [startCamera](./start-camera.md)

# startCamera

`abstract fun startCamera(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/CameraEngine.kt#L29)

Opens a camera intent to receive a captured image.

If isEnabled return true this should also be able to start a camera intent,
if isEnabled returns false this can be empty then.

