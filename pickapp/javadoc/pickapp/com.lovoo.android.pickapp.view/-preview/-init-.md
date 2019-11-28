[pickapp](../../index.md) / [com.lovoo.android.pickapp.view](../index.md) / [Preview](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Preview(pager: ViewPager, fragmentManager: FragmentManager, picker: `[`Picker`](../../com.lovoo.android.pickapp.model/-picker/index.md)`)`

UI extension to handle PickPicActivities Preview ViewPager.
It observes [Picker.SelectionState](../../com.lovoo.android.pickapp.model/-picker/-selection-state/index.md) changes and show, update or hide the Preview.
You have to call destroy if the UI is removed.

### Parameters

`pager` - the view pager from the layout

`fragmentManager` - the FragmentManager

`picker` - the Picker that will be observed