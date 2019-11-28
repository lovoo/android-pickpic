[pickcam](../../index.md) / [com.lovoo.android.pickcam.worker](../index.md) / [CaptureResultWorker](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`CaptureResultWorker(context: Context, params: WorkerParameters)`

Worker that handles all the tasks to finalize the captured image from the camera.
When this worker is done it fires a BroadcastIntent with action CaptureResultWorker.INTENT_ACTION_ON_RESULT.

Don not use the constructor directly!!!

### Parameters

`context` - the app context

`params` - the params passed to this [RxWorker](#)

**See Also**

[CaptureResultWorker.start](start.md)

[CaptureResultWorker.getUri](get-uri.md)

