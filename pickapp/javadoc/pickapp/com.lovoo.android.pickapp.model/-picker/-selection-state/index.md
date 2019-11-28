[pickapp](../../../index.md) / [com.lovoo.android.pickapp.model](../../index.md) / [Picker](../index.md) / [SelectionState](./index.md)

# SelectionState

`data class SelectionState : `[`Picker.State`](../-state/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/model/Picker.kt#L136)

State that notify over selection change within the picked items.

### Parameters

`position` - the item position within the selection or -1

`uri` - the selected [Uri](#) or null if selection is cleared

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SelectionState(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, uri: Uri?)`<br>State that notify over selection change within the picked items. |

### Properties

| Name | Summary |
|---|---|
| [position](position.md) | `val position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>the item position within the selection or -1 |
| [uri](uri.md) | `val uri: Uri?`<br>the selected [Uri](#) or null if selection is cleared |
