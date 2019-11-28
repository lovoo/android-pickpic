[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [CameraDestination](./index.md)

# CameraDestination

`interface CameraDestination : Parcelable` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/CameraDestination.kt#L19)

Model class that represents a destination for captured images.

It contains the authority for Android N and higher, the target directory and the file
within this directory.

### Properties

| Name | Summary |
|---|---|
| [authority](authority.md) | `abstract val authority: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [directory](directory.md) | `abstract val directory: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) |
| [file](file.md) | `abstract val file: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`?` |

### Extension Functions

| Name | Summary |
|---|---|
| [createTempFile](../create-temp-file.md) | `fun `[`CameraDestination`](./index.md)`.createTempFile(fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`?`<br>Stores a file in the directory of this [CameraDestination](./index.md). |
| [getUri](../get-uri.md) | `fun `[`CameraDestination`](./index.md)`.getUri(context: Context): Uri?`<br>Resolves the destination to a valid [Uri](#). If authority is set then the [Uri](#) will be resolved as content uri otherwise as absolute file [Uri](#). |

### Inheritors

| Name | Summary |
|---|---|
| [PrivateDirectory](../../com.lovoo.android.pickcore.destination/-private-directory/index.md) | `class PrivateDirectory : `[`CameraDestination`](./index.md)<br>Implementation of [CameraDestination](./index.md) for pictures that should be stored in private app directory. Keep in mind that other apps does not have access to this directory / file unless you provide a own content provider. |
| [PublicDirectory](../../com.lovoo.android.pickcore.destination/-public-directory/index.md) | `class PublicDirectory : `[`CameraDestination`](./index.md)<br>Implementation of [CameraDestination](./index.md) for pictures that should be stored in public directory. |
