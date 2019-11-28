[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [ImageEngine](index.md) / [loadImage](./load-image.md)

# loadImage

`abstract fun loadImage(context: Context, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/ImageEngine.kt#L36)

Load an image.

### Parameters

`context` - app [Context](#)

`width` - the requested width of the image

`height` - the requested height of the image

`uri` - the image [Uri](#)

`target` - the image view that show the bitmap

`errorRes` - the error drawable if request failed or 0