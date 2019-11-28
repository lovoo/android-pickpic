[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [CameraEngine](./index.md)

# CameraEngine

`interface CameraEngine` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/CameraEngine.kt#L8)

Engine interface responsible for setting up the PickPic camera.

### Functions

| Name | Summary |
|---|---|
| [getDisplayName](get-display-name.md) | `abstract fun getDisplayName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isEnabled](is-enabled.md) | `abstract fun isEnabled(gallery: `[`GalleryLib`](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determine if the Camera icon should be visible in this [GalleryLib](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md). |
| [startCamera](start-camera.md) | `abstract fun startCamera(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens a camera intent to receive a captured image. |

### Inheritors

| Name | Summary |
|---|---|
| [DisabledCameraEngine](../../com.lovoo.android.pickcore.engine/-disabled-camera-engine/index.md) | `class DisabledCameraEngine : `[`CameraEngine`](./index.md)<br>Default implementation of [CameraEngine](./index.md) that disables PickPic camera support. |
