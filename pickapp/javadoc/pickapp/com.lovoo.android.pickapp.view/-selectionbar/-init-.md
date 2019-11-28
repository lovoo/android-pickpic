[pickapp](../../index.md) / [com.lovoo.android.pickapp.view](../index.md) / [Selectionbar](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Selectionbar(picker: `[`Picker`](../../com.lovoo.android.pickapp.model/-picker/index.md)`, layout: View, dependingViews: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<View>)`

UI extension to handle the PickPicActivities Selectionbar and RecyclerView.
It observes changes emitted by [Picker](../../com.lovoo.android.pickapp.model/-picker/index.md) and add, removes or select the Thumbnails.
You have to call destroy if the UI is removed.

The action button text and click handle is customizable via setters.

### Parameters

`picker` - the Picker instance

`layout` - the bottom sheet layout.

`dependingViews` - the views that should dodge the sheet