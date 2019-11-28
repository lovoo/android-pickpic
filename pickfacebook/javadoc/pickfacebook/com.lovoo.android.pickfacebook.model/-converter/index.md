[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.model](../index.md) / [Converter](./index.md)

# Converter

`object Converter` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/model/Converter.kt#L12)

Collection of methods to convert Facebook Response data to own Data Models.

### Types

| Name | Summary |
|---|---|
| [FbAlbum](-fb-album/index.md) | `data class FbAlbum`<br>Model class that represents Facebook Album. |
| [FbAlbumData](-fb-album-data/index.md) | `data class FbAlbumData` |
| [FbCover](-fb-cover/index.md) | `data class FbCover` |
| [FbImage](-fb-image/index.md) | `data class FbImage` |
| [FbPhoto](-fb-photo/index.md) | `data class FbPhoto` |

### Functions

| Name | Summary |
|---|---|
| [convert](convert.md) | `fun convert(exception: FacebookException?): `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?`<br>Transforms the source localized message and cause to a normal [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html). |
| [convertToGalleries](convert-to-galleries.md) | `fun convertToGalleries(json: JSONObject?): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<Gallery>` |
| [convertToPictures](convert-to-pictures.md) | `fun convertToPictures(json: JSONObject?): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`FbPicture`](../-fb-picture/index.md)`>` |
