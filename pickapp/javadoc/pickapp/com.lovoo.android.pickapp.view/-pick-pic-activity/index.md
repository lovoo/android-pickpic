[pickapp](../../index.md) / [com.lovoo.android.pickapp.view](../index.md) / [PickPicActivity](./index.md)

# PickPicActivity

`class PickPicActivity : AppCompatActivity, SelectionHolder, CameraEngine, CaptureCallback` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/view/PickPicActivity.kt#L40)

Ready to use PickPic implementation. Configurable with [PickPicConfig](../../com.lovoo.android.pickapp.model/-pick-pic-config/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PickPicActivity()`<br>Ready to use PickPic implementation. Configurable with [PickPicConfig](../../com.lovoo.android.pickapp.model/-pick-pic-config/index.md). |

### Functions

| Name | Summary |
|---|---|
| [addToggleListener](add-toggle-listener.md) | `fun addToggleListener(tag: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, callback: ToggleCallback): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getDisplayName](get-display-name.md) | `fun getDisplayName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isEnabled](is-enabled.md) | `fun isEnabled(gallery: GalleryLib): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isSelected](is-selected.md) | `fun isSelected(uri: Uri): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onBackPressed](on-back-pressed.md) | `fun onBackPressed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCapture](on-capture.md) | `fun onCapture(uri: Uri?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onRequestPermissionsResult](on-request-permissions-result.md) | `fun onRequestPermissionsResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, grantResults: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onRestoreInstanceState](on-restore-instance-state.md) | `fun onRestoreInstanceState(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSaveInstanceState](on-save-instance-state.md) | `fun onSaveInstanceState(outState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeToggleListener](remove-toggle-listener.md) | `fun removeToggleListener(tag: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startCamera](start-camera.md) | `fun startCamera(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toggle](toggle.md) | `fun toggle(uri: Uri, gallery: Gallery): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [applyConfig](apply-config.md) | `fun applyConfig(intent: Intent, config: `[`PickPicConfig`](../../com.lovoo.android.pickapp.model/-pick-pic-config/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Apply the [PickPicConfig](../../com.lovoo.android.pickapp.model/-pick-pic-config/index.md) to the [Intent](#) for [PickPicActivity](./index.md). |
| [getResult](get-result.md) | `fun getResult(data: Intent?): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Uri>?`<br>Receive the [Uri](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) result from the [Intent](#). |
