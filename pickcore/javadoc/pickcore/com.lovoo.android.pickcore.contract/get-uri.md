[pickcore](../index.md) / [com.lovoo.android.pickcore.contract](index.md) / [getUri](./get-uri.md)

# getUri

`fun `[`CameraDestination`](-camera-destination/index.md)`.getUri(context: Context): Uri?` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/CameraDestination.kt#L48)

Resolves the destination to a valid [Uri](#).
If authority is set then the [Uri](#) will be resolved as content uri otherwise as absolute file [Uri](#).

### Parameters

`context` - app context

**Return**
the [Uri](#) for this file if possible

