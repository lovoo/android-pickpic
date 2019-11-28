[pickui](../../index.md) / [com.lovoo.android.pickui.adapter](../index.md) / [PictureAdapter](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`PictureAdapter(context: Context, selectionLookUp: (Picture) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, onClick: (View, Picture) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`

RecyclerView Adapter to present [Picture](#)s.

It supports two view types:

* camera button
* selectable picture image

### Parameters

`context` - app context

`selectionLookUp` - determine if the [Picture](#) is selected

`onClick` - the callback when an entry was clicked