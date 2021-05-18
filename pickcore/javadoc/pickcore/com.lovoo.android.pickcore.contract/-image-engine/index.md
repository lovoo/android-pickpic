[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [ImageEngine](./index.md)

# ImageEngine

`interface ImageEngine` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/ImageEngine.kt#L28)

Engine interface responsible for setting up the PickPic image loading.

**See Also**

[com.lovoo.android.pickcore.engine.DefaultImageEngine](../../com.lovoo.android.pickcore.engine/-default-image-engine/index.md)

[com.lovoo.android.pickapp.factory.GlideEngine](#)

### Functions

| Name | Summary |
|---|---|
| [loadImage](load-image.md) | `abstract fun loadImage(context: Context, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, corner: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load an image. |
| [loadThumbnail](load-thumbnail.md) | `abstract fun loadThumbnail(context: Context, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri, target: ImageView, corner: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, errorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load a small image. |

### Inheritors

| Name | Summary |
|---|---|
| [DefaultImageEngine](../../com.lovoo.android.pickcore.engine/-default-image-engine/index.md) | `class DefaultImageEngine : `[`ImageEngine`](./index.md)<br>Default implementation of [ImageEngine](./index.md) that loads images without any strategy or cache. Be aware that this should never be used in production, because of OutOfMemory-Exceptions. |
