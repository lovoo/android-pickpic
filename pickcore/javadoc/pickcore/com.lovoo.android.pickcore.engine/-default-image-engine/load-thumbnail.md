[pickcore](../../index.md) / [com.lovoo.android.pickcore.engine](../index.md) / [DefaultImageEngine](index.md) / [loadThumbnail](./load-thumbnail.md)

# loadThumbnail

`fun loadThumbnail(context: Context, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, corner: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/engine/DefaultImageEngine.kt#L33)

Overrides [ImageEngine.loadThumbnail](../../com.lovoo.android.pickcore.contract/-image-engine/load-thumbnail.md)

Load a small image.

### Parameters

`context` - app [Context](#)

`size` - the requested width and height of the image

`uri` - the image [Uri](#)

`target` - the image view that show the bitmap

`corner` - the rounded corner in pixel

`errorRes` - the error drawable if request failed or 0