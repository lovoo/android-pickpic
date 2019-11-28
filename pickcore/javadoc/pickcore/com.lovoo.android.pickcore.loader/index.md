[pickcore](../index.md) / [com.lovoo.android.pickcore.loader](./index.md)

## Package com.lovoo.android.pickcore.loader

### Types

| Name | Summary |
|---|---|
| [CameraLoader](-camera-loader/index.md) | `object CameraLoader`<br>Object class that helps to start and finish a capture [Intent](#) on Android. |
| [Collector](-collector/index.md) | `class Collector : LoaderCallbacks<Cursor>`<br>An [LoaderManager.LoaderCallbacks](#) implementation that load a cursor and set the [MutableLiveData](#) cursor. Others can observe this. |
| [GalleryLoader](-gallery-loader/index.md) | `class GalleryLoader : CursorLoader`<br>A [CursorLoader](#) implementation that fetch album information from external [MediaStore.Files](#) database. The current [Cursor](#) should be converted to [GalleryLib](../com.lovoo.android.pickcore.model/-gallery-lib/index.md) model. |
| [PictureLoader](-picture-loader/index.md) | `class PictureLoader : CursorLoader`<br>A [CursorLoader](#) implementation that fetch picture information from external [MediaStore.Files](#) database for the given [GalleryLib](../com.lovoo.android.pickcore.model/-gallery-lib/index.md). The current [Cursor](#) should be converted to [PictureLib](../com.lovoo.android.pickcore.model/-picture-lib/index.md) model. |
