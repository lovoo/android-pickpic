[pickui](../../index.md) / [com.lovoo.android.pickui.adapter](../index.md) / [PictureAdapter](./index.md)

# PictureAdapter

`class PictureAdapter : RecyclerViewCursorAdapter<ViewHolder>` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickui/src/main/kotlin/com/lovoo/android/pickui/adapter/PictureAdapter.kt#L27)

RecyclerView Adapter to present [Picture](#)s.

It supports two view types:

* camera button
* selectable picture image

### Parameters

`context` - app context

`selectionLookUp` - determine if the [Picture](#) is selected

`onClick` - the callback when an entry was clicked

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PictureAdapter(context: Context, selectionLookUp: (Picture) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, onClick: (View, Picture) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>RecyclerView Adapter to present [Picture](#)s. |

### Functions

| Name | Summary |
|---|---|
| [getItemViewType](get-item-view-type.md) | `fun getItemViewType(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: ViewHolder, cursor: Cursor): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ViewHolder` |
| [onViewRecycled](on-view-recycled.md) | `fun onViewRecycled(holder: ViewHolder): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
