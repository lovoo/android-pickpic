[pickapp](../../index.md) / [com.lovoo.android.pickapp.factory](../index.md) / [GlideEngine](index.md) / [loadThumbnail](./load-thumbnail.md)

# loadThumbnail

`fun loadThumbnail(context: Context, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/factory/GlideEngine.kt#L26)

Load images from [Uri](#) with centerCrop and fixed square size.

### Parameters

`context` - the app context

`size` - the requested image width and height, must be greater then 0

`uri` - the requested local or remote [Uri](#) to the picture

`target` - the [ImageView](#) that should receive the Bitmap

`errorRes` - the optional resource as error fallback