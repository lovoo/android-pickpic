[pickcore](../index.md) / [com.lovoo.android.pickcore.contract](./index.md)

## Package com.lovoo.android.pickcore.contract

### Types

| Name | Summary |
|---|---|
| [CameraDestination](-camera-destination/index.md) | `interface CameraDestination : Parcelable`<br>Model class that represents a destination for captured images. |
| [CameraEngine](-camera-engine/index.md) | `interface CameraEngine`<br>Engine interface responsible for setting up the PickPic camera. |
| [GalleryPresenter](-gallery-presenter/index.md) | `interface GalleryPresenter`<br>Contract to implement for Presenter. |
| [GalleryView](-gallery-view/index.md) | `interface GalleryView`<br>Contract to implement for UI. |
| [ImageEngine](-image-engine/index.md) | `interface ImageEngine`<br>Engine interface responsible for setting up the PickPic image loading. |
| [PicturePresenter](-picture-presenter/index.md) | `interface PicturePresenter`<br>Contract to implement for Presenter. |
| [PictureView](-picture-view/index.md) | `interface PictureView`<br>Contract to implement for UI. |
| [SelectionHolder](-selection-holder/index.md) | `interface SelectionHolder`<br>Contract for the selection implementation. |
| [ToggleCallback](-toggle-callback/index.md) | `interface ToggleCallback` |

### Functions

| Name | Summary |
|---|---|
| [createTempFile](create-temp-file.md) | `fun `[`CameraDestination`](-camera-destination/index.md)`.createTempFile(fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`?`<br>Stores a file in the directory of this [CameraDestination](-camera-destination/index.md). |
| [getUri](get-uri.md) | `fun `[`CameraDestination`](-camera-destination/index.md)`.getUri(context: Context): Uri?`<br>Resolves the destination to a valid [Uri](#). If authority is set then the [Uri](#) will be resolved as content uri otherwise as absolute file [Uri](#). |
