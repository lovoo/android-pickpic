[pickapp](../../index.md) / [com.lovoo.android.pickapp.factory](../index.md) / [GlideEngine](./index.md)

# GlideEngine

`class GlideEngine : ImageEngine` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/pickapp/src/main/kotlin/com/lovoo/android/pickapp/factory/GlideEngine.kt#L32)

Implementation of [ImageEngine](#) to use [Glide](#) within PickPic.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GlideEngine()`<br>Implementation of [ImageEngine](#) to use [Glide](#) within PickPic. |

### Functions

| Name | Summary |
|---|---|
| [loadImage](load-image.md) | `fun loadImage(context: Context, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, corner: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load images from [Uri](#) with centerCrop and fixed width and height. |
| [loadThumbnail](load-thumbnail.md) | `fun loadThumbnail(context: Context, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, corner: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load images from [Uri](#) with centerCrop and fixed square size. |
