[pickcore](../../index.md) / [com.lovoo.android.pickcore.model](../index.md) / [GalleryLib](./index.md)

# GalleryLib

`data class GalleryLib` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/model/GalleryLib.kt#L26)

Model class that represents an Android GalleryLib Folder loaded from the Database.

### Parameters

`id` - the gallery Identifier within the database

`coverPath` - the path to the cover image

`name` - the gallery name

`count` - the amount of pictures within this gallery

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GalleryLib(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "", coverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "", name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "", count: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = 0L)`<br>Model class that represents an Android GalleryLib Folder loaded from the Database. |

### Properties

| Name | Summary |
|---|---|
| [count](count.md) | `val count: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?`<br>the amount of pictures within this gallery |
| [coverPath](cover-path.md) | `val coverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>the path to the cover image |
| [id](id.md) | `val id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>the gallery Identifier within the database |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>the gallery name |

### Extension Functions

| Name | Summary |
|---|---|
| [convertToUi](../convert-to-ui.md) | `fun `[`GalleryLib`](./index.md)`.convertToUi(): `[`Gallery`](../-gallery/index.md)<br>Convert a Library Gallery to PickPic Gallery |
