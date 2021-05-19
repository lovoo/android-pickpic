[pickcore](../../index.md) / [com.lovoo.android.pickcore.engine](../index.md) / [DefaultImageEngine](index.md) / [loadImage](./load-image.md)

# loadImage

`fun loadImage(context: Context, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, corner: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/engine/DefaultImageEngine.kt#L37)

Overrides [ImageEngine.loadImage](../../com.lovoo.android.pickcore.contract/-image-engine/load-image.md)

Load an image.

### Parameters

`context` - app [Context](#)

`width` - the requested width of the image

`height` - the requested height of the image

`uri` - the image [Uri](#)

`target` - the image view that show the bitmap

`corner` - the rounded corner in pixel

`errorRes` - the error drawable if request failed or 0