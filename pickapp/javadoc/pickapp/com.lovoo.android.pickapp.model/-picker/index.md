[pickapp](../../index.md) / [com.lovoo.android.pickapp.model](../index.md) / [Picker](./index.md)

# Picker

`class Picker` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/model/Picker.kt#L17)

The data source for PickPicActivity.
It holds a map of picked Uris with the depending [Gallery](#).
[State](-state/index.md) changes are emitted via observable and should be disposed.

### Parameters

`config` - the [PickConfig](-pick-config/index.md) to determine the limits

### Types

| Name | Summary |
|---|---|
| [AddState](-add-state/index.md) | `data class AddState : `[`Picker.State`](-state/index.md)<br>State that notifies that a new item was picked. |
| [PickConfig](-pick-config/index.md) | `data class PickConfig` |
| [RemoveState](-remove-state/index.md) | `data class RemoveState : `[`Picker.State`](-state/index.md)<br>State that notifies that a picked item was removed. |
| [SelectionState](-selection-state/index.md) | `data class SelectionState : `[`Picker.State`](-state/index.md)<br>State that notify over selection change within the picked items. |
| [State](-state/index.md) | `class State` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Picker(config: `[`Picker.PickConfig`](-pick-config/index.md)`)`<br>The data source for PickPicActivity. It holds a map of picked Uris with the depending [Gallery](#). [State](-state/index.md) changes are emitted via observable and should be disposed. |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`Picker.PickConfig`](-pick-config/index.md)<br>the [PickConfig](-pick-config/index.md) to determine the limits |
| [map](map.md) | `val map: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<Uri, Gallery>`<br>The data map of picked items. |

### Functions

| Name | Summary |
|---|---|
| [getObservable](get-observable.md) | `fun getObservable(): Observable<`[`Picker.State`](-state/index.md)`>`<br>The [State](-state/index.md) observable that emit different state changes. |
| [getPickedUris](get-picked-uris.md) | `fun getPickedUris(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Uri>`<br>A non null list of picked Uris from the map. |
| [remove](remove.md) | `fun remove(uri: Uri): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Explicitly remove a picture to the map. |
| [select](select.md) | `fun select(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Select an item by position, if not found the selection will be cleaned.`fun select(uri: Uri?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Select an item, if null the selection will be cleaned. |
| [toggle](toggle.md) | `fun toggle(uri: Uri, gallery: Gallery): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Add or remove a picture to the map.`fun toggle(uris: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<Uri>, galleries: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<Gallery>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add or remove multiple items. This calls toggle(picture: Picture, gallery: Gallery) for each entry. Please make sure that both Arrays are equally sized. |
