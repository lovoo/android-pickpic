[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [ImageEngine](index.md) / [loadThumbnail](./load-thumbnail.md)

# loadThumbnail

`abstract fun loadThumbnail(context: Context, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/ImageEngine.kt#L24)

Load a small image.

### Parameters

`context` - app [Context](#)

`size` - the requested width and height of the image

`uri` - the image [Uri](#)

`target` - the image view that show the bitmap

`errorRes` - the error drawable if request failed or 0