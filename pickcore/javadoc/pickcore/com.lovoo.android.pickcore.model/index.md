[pickcore](../index.md) / [com.lovoo.android.pickcore.model](./index.md)

## Package com.lovoo.android.pickcore.model

### Types

| Name | Summary |
|---|---|
| [Gallery](-gallery/index.md) | `data class Gallery : Parcelable`<br>Model class that represents an PickPic Gallery Folder. |
| [GalleryLib](-gallery-lib/index.md) | `data class GalleryLib`<br>Model class that represents an Android GalleryLib Folder loaded from the Database. |
| [Picture](-picture/index.md) | `data class Picture : Parcelable`<br>Model class that represents an PickPic Picture. |
| [PictureLib](-picture-lib/index.md) | `data class PictureLib`<br>Model class that represents an Android PictureLib loaded from the Database. |

### Functions

| Name | Summary |
|---|---|
| [convertToLib](convert-to-lib.md) | `fun `[`Picture`](-picture/index.md)`.convertToLib(): `[`PictureLib`](-picture-lib/index.md)<br>Convert a PickPic Picture to Library Picture`fun `[`Gallery`](-gallery/index.md)`.convertToLib(): `[`GalleryLib`](-gallery-lib/index.md)<br>Convert a PickPic Gallery to Library Gallery |
| [convertToUi](convert-to-ui.md) | `fun `[`PictureLib`](-picture-lib/index.md)`.convertToUi(): `[`Picture`](-picture/index.md)<br>Convert a Library Picture to PickPic Picture`fun `[`GalleryLib`](-gallery-lib/index.md)`.convertToUi(): `[`Gallery`](-gallery/index.md)<br>Convert a Library Gallery to PickPic Gallery |
