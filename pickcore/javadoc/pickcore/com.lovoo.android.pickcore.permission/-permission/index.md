[pickcore](../../index.md) / [com.lovoo.android.pickcore.permission](../index.md) / [Permission](./index.md)

# Permission

`object Permission` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/permission/Permission.kt#L33)

A small solution for Android Permission handle.

### Properties

| Name | Summary |
|---|---|
| [cameraPermissions](camera-permissions.md) | `val cameraPermissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>List of permission that are needed to capture images from the camera. |
| [galleryPermissions](gallery-permissions.md) | `val galleryPermissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>List of permissions that are needed to load images from the device. |

### Functions

| Name | Summary |
|---|---|
| [getDeniedPermissions](get-denied-permissions.md) | `fun getDeniedPermissions(activity: Activity, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>>`<br>Validate if all permissions are granted (result empty). |
| [openSettings](open-settings.md) | `fun openSettings(activity: Activity): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Navigate to the App setting. To enable permissions that were denied always. |
| [requestPermissions](request-permissions.md) | `fun requestPermissions(fragment: Fragment, requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun requestPermissions(activity: Activity, requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Request a set of permissions. |
