[pickcam](../../index.md) / [com.lovoo.android.pickcam.worker](../index.md) / [CaptureResultWorker](index.md) / [start](./start.md)

# start

`fun start(destination: CameraDestination, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "CaptureResultWorker"): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcam/src/main/kotlin/com/lovoo/android/pickcam/worker/CaptureResultWorker.kt#L99)

Enqueue the unique [Worker](#) with REPLACE policy.

### Parameters

`destination` - the [CameraDestination](#) that was used to capture the picture

`name` - optional unique name for the [Worker](#) (default: CaptureResultWorker)