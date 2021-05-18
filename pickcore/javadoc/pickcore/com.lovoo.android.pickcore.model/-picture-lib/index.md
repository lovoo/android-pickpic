[pickcore](../../index.md) / [com.lovoo.android.pickcore.model](../index.md) / [PictureLib](./index.md)

# PictureLib

`data class PictureLib` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/model/PictureLib.kt#L25)

Model class that represents an Android PictureLib loaded from the Database.

### Parameters

`id` - the picture Identifier within the database

`mimeType` - the file type of the picture or null

`size` - the file size or null

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PictureLib(id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, mimeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "", size: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = 0L)`<br>Model class that represents an Android PictureLib loaded from the Database. |

### Properties

| Name | Summary |
|---|---|
| [id](id.md) | `val id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>the picture Identifier within the database |
| [mimeType](mime-type.md) | `val mimeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>the file type of the picture or null |
| [size](size.md) | `val size: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?`<br>the file size or null |

### Extension Functions

| Name | Summary |
|---|---|
| [convertToUi](../convert-to-ui.md) | `fun `[`PictureLib`](./index.md)`.convertToUi(): `[`Picture`](../-picture/index.md)<br>Convert a Library Picture to PickPic Picture |
