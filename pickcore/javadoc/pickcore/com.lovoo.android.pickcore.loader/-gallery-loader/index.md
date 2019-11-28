[pickcore](../../index.md) / [com.lovoo.android.pickcore.loader](../index.md) / [GalleryLoader](./index.md)

# GalleryLoader

`class GalleryLoader : CursorLoader` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/loader/GalleryLoader.kt#L21)

A [CursorLoader](#) implementation that fetch album information from external [MediaStore.Files](#)
database. The current [Cursor](#) should be converted to [GalleryLib](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md) model.

### Parameters

`context` - the app [Context](#)

**See Also**

[GalleryLoader.convert](convert.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GalleryLoader(context: Context)`<br>A [CursorLoader](#) implementation that fetch album information from external [MediaStore.Files](#) database. The current [Cursor](#) should be converted to [GalleryLib](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md) model. |

### Functions

| Name | Summary |
|---|---|
| [loadInBackground](load-in-background.md) | `fun loadInBackground(): Cursor` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [convert](convert.md) | `fun convert(cursor: Cursor): `[`GalleryLib`](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md)<br>Converts the current [Cursor](#) entry to [GalleryLib](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md) model. |
| [newInstance](new-instance.md) | `fun newInstance(context: Context): CursorLoader`<br>Creates a new instance of [GalleryLoader](./index.md). |
