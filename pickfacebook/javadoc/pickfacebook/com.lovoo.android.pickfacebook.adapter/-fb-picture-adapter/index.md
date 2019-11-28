[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.adapter](../index.md) / [FbPictureAdapter](./index.md)

# FbPictureAdapter

`class FbPictureAdapter : Adapter<ViewHolder>` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/adapter/FbPictureAdapter.kt#L21)

RecyclerView Adapter to present [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)s.

### Parameters

`context` - app context

`selectionLookUp` - determine if the [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md) is selected

`onClick` - the callback when an entry was clicked

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FbPictureAdapter(context: Context, selectionLookUp: (`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, onClick: (View, `[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>RecyclerView Adapter to present [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)s. |

### Functions

| Name | Summary |
|---|---|
| [add](add.md) | `fun add(list: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add additional items to the list. |
| [clear](clear.md) | `fun clear(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the whole list of [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)s |
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [indexOf](index-of.md) | `fun indexOf(filter: (`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: ViewHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ViewHolder` |
| [onViewRecycled](on-view-recycled.md) | `fun onViewRecycled(holder: ViewHolder): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
