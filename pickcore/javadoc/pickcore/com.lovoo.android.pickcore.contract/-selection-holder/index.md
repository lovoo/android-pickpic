[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [SelectionHolder](./index.md)

# SelectionHolder

`interface SelectionHolder` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/SelectionHolder.kt#L26)

Contract for the selection implementation.

**See Also**

[com.lovoo.android.pickapp.view.PickPicActivity](#)

### Functions

| Name | Summary |
|---|---|
| [addToggleListener](add-toggle-listener.md) | `abstract fun addToggleListener(tag: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, callback: `[`ToggleCallback`](../-toggle-callback/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add a listener to subscribe for selection changes. |
| [isSelected](is-selected.md) | `abstract fun isSelected(uri: Uri): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [removeToggleListener](remove-toggle-listener.md) | `abstract fun removeToggleListener(tag: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Remove a registered listener. |
| [toggle](toggle.md) | `abstract fun toggle(uri: Uri, gallery: `[`Gallery`](../../com.lovoo.android.pickcore.model/-gallery/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Select or unselect a certain [Uri](#). |
