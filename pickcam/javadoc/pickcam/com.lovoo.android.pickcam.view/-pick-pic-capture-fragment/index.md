[pickcam](../../index.md) / [com.lovoo.android.pickcam.view](../index.md) / [PickPicCaptureFragment](./index.md)

# PickPicCaptureFragment

`class PickPicCaptureFragment : DialogFragment` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcam/src/main/kotlin/com/lovoo/android/pickcam/view/PickPicCaptureFragment.kt#L35)

Ready to use solution to handle Android Camera capture.

Use PickPicCaptureFragment.start() and let your [Activity](#)
or parent [Fragment](#) implement [CaptureCallback](-capture-callback/index.md).

It is based on [DialogFragment](#) and will be visible
shortly as an overlay.

Modify pickapp proguard rules as well when package is changed or class is renamed!!!

### Types

| Name | Summary |
|---|---|
| [CaptureCallback](-capture-callback/index.md) | `interface CaptureCallback`<br>The interface that the calling UI should implement to receive the captured file. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PickPicCaptureFragment()`<br>Ready to use solution to handle Android Camera capture. |

### Functions

| Name | Summary |
|---|---|
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAttach](on-attach.md) | `fun onAttach(context: Context?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?` |
| [onDetach](on-detach.md) | `fun onDetach(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onRequestPermissionsResult](on-request-permissions-result.md) | `fun onRequestPermissionsResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, grantResults: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSaveInstanceState](on-save-instance-state.md) | `fun onSaveInstanceState(outState: Bundle): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onViewCreated](on-view-created.md) | `fun onViewCreated(view: View, savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [start](start.md) | `fun start(fragmentManager: FragmentManager, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
