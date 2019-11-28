[pickcore](../index.md) / [com.lovoo.android.pickcore.destination](./index.md)

## Package com.lovoo.android.pickcore.destination

### Types

| Name | Summary |
|---|---|
| [PrivateDirectory](-private-directory/index.md) | `class PrivateDirectory : `[`CameraDestination`](../com.lovoo.android.pickcore.contract/-camera-destination/index.md)<br>Implementation of [CameraDestination](../com.lovoo.android.pickcore.contract/-camera-destination/index.md) for pictures that should be stored in private app directory. Keep in mind that other apps does not have access to this directory / file unless you provide a own content provider. |
| [PublicDirectory](-public-directory/index.md) | `class PublicDirectory : `[`CameraDestination`](../com.lovoo.android.pickcore.contract/-camera-destination/index.md)<br>Implementation of [CameraDestination](../com.lovoo.android.pickcore.contract/-camera-destination/index.md) for pictures that should be stored in public directory. |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [java.io.File](java.io.-file/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [moveToPublicDirectory](move-to-public-directory.md) | `fun `[`PrivateDirectory`](-private-directory/index.md)`.moveToPublicDirectory(): `[`PublicDirectory`](-public-directory/index.md)<br>Copies the file to the default [PublicDirectory](-public-directory/index.md) and deletes it from the private directory. |
