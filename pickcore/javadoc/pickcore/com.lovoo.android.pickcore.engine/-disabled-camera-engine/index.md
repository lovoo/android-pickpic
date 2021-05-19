[pickcore](../../index.md) / [com.lovoo.android.pickcore.engine](../index.md) / [DisabledCameraEngine](./index.md)

# DisabledCameraEngine

`class DisabledCameraEngine : `[`CameraEngine`](../../com.lovoo.android.pickcore.contract/-camera-engine/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/engine/DisabledCameraEngine.kt#L29)

Default implementation of [CameraEngine](../../com.lovoo.android.pickcore.contract/-camera-engine/index.md) that disables PickPic camera support.

isEnabled returns always false and
startCamera executes nothing

**Ee**
[com.lovoo.android.pickcore.loader.PictureLoader](../../com.lovoo.android.pickcore.loader/-picture-loader/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DisabledCameraEngine()`<br>Default implementation of [CameraEngine](../../com.lovoo.android.pickcore.contract/-camera-engine/index.md) that disables PickPic camera support. |

### Functions

| Name | Summary |
|---|---|
| [getDisplayName](get-display-name.md) | `fun getDisplayName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isEnabled](is-enabled.md) | `fun isEnabled(gallery: `[`GalleryLib`](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determine if the Camera icon should be visible in this [GalleryLib](../../com.lovoo.android.pickcore.model/-gallery-lib/index.md). |
| [startCamera](start-camera.md) | `fun startCamera(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens a camera intent to receive a captured image. |
