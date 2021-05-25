[pickcore](../../index.md) / [com.lovoo.android.pickcore.destination](../index.md) / [PublicDirectory](./index.md)

# PublicDirectory

`class PublicDirectory : `[`CameraDestination`](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/destination/PublicDirectory.kt#L28)

Implementation of [CameraDestination](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md) for pictures that should be stored in public directory.

### Types

| Name | Summary |
|---|---|
| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : Creator<`[`PublicDirectory`](./index.md)`>` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PublicDirectory(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = Environment.DIRECTORY_PICTURES, fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "")` |

### Properties

| Name | Summary |
|---|---|
| [authority](authority.md) | `val authority: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [directory](directory.md) | `val directory: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) |
| [file](file.md) | `val file: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`?` |

### Functions

| Name | Summary |
|---|---|
| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: Parcel, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: Parcel): `[`PublicDirectory`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`PublicDirectory`](./index.md)`?>` |

### Extension Functions

| Name | Summary |
|---|---|
| [createTempFile](../../com.lovoo.android.pickcore.contract/create-temp-file.md) | `fun `[`CameraDestination`](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md)`.createTempFile(fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`?`<br>Stores a file in the directory of this [CameraDestination](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md). |
| [getUri](../../com.lovoo.android.pickcore.contract/get-uri.md) | `fun `[`CameraDestination`](../../com.lovoo.android.pickcore.contract/-camera-destination/index.md)`.getUri(context: Context): Uri?`<br>Resolves the destination to a valid [Uri](#). If authority is set then the [Uri](#) will be resolved as content uri otherwise as absolute file [Uri](#). |
