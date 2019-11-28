[pickcam](../../index.md) / [com.lovoo.android.pickcam.worker](../index.md) / [CaptureResultWorker](./index.md)

# CaptureResultWorker

`class CaptureResultWorker : RxWorker` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcam/src/main/kotlin/com/lovoo/android/pickcam/worker/CaptureResultWorker.kt#L28)

Worker that handles all the tasks to finalize the captured image from the camera.
When this worker is done it fires a BroadcastIntent with action CaptureResultWorker.INTENT_ACTION_ON_RESULT.

Don not use the constructor directly!!!

### Parameters

`context` - the app context

`params` - the params passed to this [RxWorker](#)

**See Also**

[CaptureResultWorker.start](start.md)

[CaptureResultWorker.getUri](get-uri.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CaptureResultWorker(context: Context, params: WorkerParameters)`<br>Worker that handles all the tasks to finalize the captured image from the camera. When this worker is done it fires a BroadcastIntent with action CaptureResultWorker.INTENT_ACTION_ON_RESULT. |

### Functions

| Name | Summary |
|---|---|
| [createWork](create-work.md) | `fun createWork(): Single<Result>` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [INTENT_ACTION_ON_RESULT](-i-n-t-e-n-t_-a-c-t-i-o-n_-o-n_-r-e-s-u-l-t.md) | `const val INTENT_ACTION_ON_RESULT: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [getUri](get-uri.md) | `fun getUri(intent: Intent?): Uri?`<br>Receive the [Uri](#) from the [Intent](#) provided by the [BroadcastReceiver](#) |
| [start](start.md) | `fun start(destination: CameraDestination, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "CaptureResultWorker"): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Enqueue the unique [Worker](#) with REPLACE policy. |
