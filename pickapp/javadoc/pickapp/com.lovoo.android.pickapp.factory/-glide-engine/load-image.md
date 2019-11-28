[pickapp](../../index.md) / [com.lovoo.android.pickapp.factory](../index.md) / [GlideEngine](index.md) / [loadImage](./load-image.md)

# loadImage

`fun loadImage(context: Context, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, @DrawableRes errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/factory/GlideEngine.kt#L41)

Load images from [Uri](#) with centerCrop and fixed width and height.

### Parameters

`context` - the app context

`width` - the requested image width, must be greater then 0

`height` - the requested image height, must be greater then 0

`uri` - the requested local or remote [Uri](#) to the picture

`target` - the [ImageView](#) that should receive the Bitmap

`errorRes` - the optional resource as error fallback