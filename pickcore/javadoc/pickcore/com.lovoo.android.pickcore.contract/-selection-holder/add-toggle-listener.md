[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [SelectionHolder](index.md) / [addToggleListener](./add-toggle-listener.md)

# addToggleListener

`abstract fun addToggleListener(tag: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, callback: `[`ToggleCallback`](../-toggle-callback/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/SelectionHolder.kt#L32)

Add a listener to subscribe for selection changes.

### Parameters

`tag` - a unique Any-Object for the listener, see removeToggleListener(tag: Any)

`callback` - the callback that should be triggered