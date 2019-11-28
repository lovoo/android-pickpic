[pickui](../../index.md) / [com.lovoo.android.pickui.adapter](../index.md) / [ViewHolder](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ViewHolder(view: View, engine: ImageEngine, selectionLookUp: (`[`T`](index.md#T)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, onClick: (View, `[`T`](index.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`

Typed [RecyclerView.ViewHolder](#) implementation that enforce its own width as height.

### Parameters

`view` - the current view for this [RecyclerView.ViewHolder](#)

`engine` - the [ImageEngine](#) to load pictures

`selectionLookUp` - determine if the item is selected

`onClick` - click callback for this item