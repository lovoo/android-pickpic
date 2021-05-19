[pickcore](../../index.md) / [com.lovoo.android.pickcore.engine](../index.md) / [DefaultImageEngine](./index.md)

# DefaultImageEngine

`class DefaultImageEngine : `[`ImageEngine`](../../com.lovoo.android.pickcore.contract/-image-engine/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/engine/DefaultImageEngine.kt#L32)

Default implementation of [ImageEngine](../../com.lovoo.android.pickcore.contract/-image-engine/index.md) that loads images without any strategy or cache.
Be aware that this should never be used in production, because of OutOfMemory-Exceptions.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DefaultImageEngine()`<br>Default implementation of [ImageEngine](../../com.lovoo.android.pickcore.contract/-image-engine/index.md) that loads images without any strategy or cache. Be aware that this should never be used in production, because of OutOfMemory-Exceptions. |

### Functions

| Name | Summary |
|---|---|
| [loadImage](load-image.md) | `fun loadImage(context: Context, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, corner: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load an image. |
| [loadThumbnail](load-thumbnail.md) | `fun loadThumbnail(context: Context, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, corner: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load a small image. |
