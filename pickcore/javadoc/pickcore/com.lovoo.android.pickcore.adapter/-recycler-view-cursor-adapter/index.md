[pickcore](../../index.md) / [com.lovoo.android.pickcore.adapter](../index.md) / [RecyclerViewCursorAdapter](./index.md)

# RecyclerViewCursorAdapter

`abstract class RecyclerViewCursorAdapter<VH : ViewHolder> : Adapter<`[`VH`](index.md#VH)`>` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/adapter/RecyclerViewCursorAdapter.kt#L30)

An [RecyclerView.Adapter](#) implementation that handle [Cursor](#) as data source.

### Parameters

`cursor` - the loaded [Cursor](#) or null by default

**See Also**

[RecyclerViewCursorAdapter.changeCursor](change-cursor.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RecyclerViewCursorAdapter(cursor: Cursor? = null)`<br>An [RecyclerView.Adapter](#) implementation that handle [Cursor](#) as data source. |

### Functions

| Name | Summary |
|---|---|
| [changeCursor](change-cursor.md) | `fun changeCursor(cursor: Cursor?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Replace the current [Cursor](#) with a new one and close the old one if needed. |
| [getItem](get-item.md) | `fun <T> getItem(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, transform: (Cursor) -> `[`T`](get-item.md#T)`): `[`T`](get-item.md#T)`?` |
| [getItemCount](get-item-count.md) | `open fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItemId](get-item-id.md) | `open fun getItemId(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getItems](get-items.md) | `fun <T> getItems(transform: (Cursor) -> `[`T`](get-items.md#T)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`T`](get-items.md#T)`>` |
| [onBindViewHolder](on-bind-view-holder.md) | `abstract fun onBindViewHolder(holder: `[`VH`](index.md#VH)`, cursor: Cursor): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun onBindViewHolder(holder: `[`VH`](index.md#VH)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
