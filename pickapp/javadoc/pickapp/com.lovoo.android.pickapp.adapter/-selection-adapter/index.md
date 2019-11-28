[pickapp](../../index.md) / [com.lovoo.android.pickapp.adapter](../index.md) / [SelectionAdapter](./index.md)

# SelectionAdapter

`class SelectionAdapter : Adapter<`[`SelectionAdapter.ViewHolder`](-view-holder/index.md)`>` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/adapter/SelectionAdapter.kt#L20)

[RecyclerView.Adapter](#) for the selection bar.

### Types

| Name | Summary |
|---|---|
| [ViewHolder](-view-holder/index.md) | `class ViewHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SelectionAdapter()`<br>[RecyclerView.Adapter](#) for the selection bar. |

### Properties

| Name | Summary |
|---|---|
| [onClickListener](on-click-listener.md) | `var onClickListener: ((View, Uri) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)?`<br>The click listener for views. Changes take effect when views are bind again. |
| [selectedUri](selected-uri.md) | `var selectedUri: Uri?`<br>Set the selected item to notify the adapter to update the ui state. |

### Functions

| Name | Summary |
|---|---|
| [add](add.md) | `fun add(uri: Uri): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [clear](clear.md) | `fun clear(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the selection list. |
| [get](get.md) | `fun get(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Uri?` |
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`SelectionAdapter.ViewHolder`](-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`SelectionAdapter.ViewHolder`](-view-holder/index.md) |
| [remove](remove.md) | `fun remove(uri: Uri): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
