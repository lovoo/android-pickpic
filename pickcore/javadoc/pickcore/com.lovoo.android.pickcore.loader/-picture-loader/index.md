[pickcore](../../index.md) / [com.lovoo.android.pickcore.loader](../index.md) / [PictureLoader](./index.md)

# PictureLoader

`class PictureLoader : CursorLoader` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/loader/PictureLoader.kt#L25)

A [CursorLoader](#) implementation that fetch picture information from external [MediaStore.Files](#)
database for the given [GalleryLib](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md). The current [Cursor](#) should be converted to [PictureLib](../../com.lovoo.android.pickcore.model/-picture-lib/index.md) model.

### Parameters

`context` - the app [Context](#)

`gallery` - the current selected [GalleryLib](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md)

**See Also**

[PictureLoader.convert](convert.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PictureLoader(context: Context, gallery: `[`GalleryLib`](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md)`)`<br>A [CursorLoader](#) implementation that fetch picture information from external [MediaStore.Files](#) database for the given [GalleryLib](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md). The current [Cursor](#) should be converted to [PictureLib](../../com.lovoo.android.pickcore.model/-picture-lib/index.md) model. |

### Functions

| Name | Summary |
|---|---|
| [loadInBackground](load-in-background.md) | `fun loadInBackground(): Cursor?` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [CAMERA_ID](-c-a-m-e-r-a_-i-d.md) | `const val CAMERA_ID: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [convert](convert.md) | `fun convert(cursor: Cursor): `[`PictureLib`](../../com.lovoo.android.pickcore.model/-picture-lib/index.md)<br>Converts the current [Cursor](#) entry to [PictureLib](../../com.lovoo.android.pickcore.model/-picture-lib/index.md) model. |
| [newInstance](new-instance.md) | `fun newInstance(context: Context, gallery: `[`GalleryLib`](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md)`): CursorLoader`<br>Creates a new instance of [PictureLoader](./index.md). |
