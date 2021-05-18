[pickapp](../../index.md) / [com.lovoo.android.pickapp.view](../index.md) / [Preview](./index.md)

# Preview

`class Preview` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/pickapp/src/main/kotlin/com/lovoo/android/pickapp/view/Preview.kt#L35)

UI extension to handle PickPicActivities Preview ViewPager.
It observes [Picker.State](../../com.lovoo.android.pickapp.model/-picker/-state/index.md) changes and show, update or hide the Preview.
You have to call destroy if the UI is removed.

### Parameters

`pager` - the view pager from the layout

`fragmentManager` - the FragmentManager

`picker` - the Picker that will be observed

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Preview(pager: ViewPager, fragmentManager: FragmentManager, picker: `[`Picker`](../../com.lovoo.android.pickapp.model/-picker/index.md)`)`<br>UI extension to handle PickPicActivities Preview ViewPager. It observes [Picker.State](../../com.lovoo.android.pickapp.model/-picker/-state/index.md) changes and show, update or hide the Preview. You have to call destroy if the UI is removed. |

### Properties

| Name | Summary |
|---|---|
| [fragmentManager](fragment-manager.md) | `val fragmentManager: FragmentManager`<br>the FragmentManager |
| [pager](pager.md) | `val pager: ViewPager`<br>the view pager from the layout |
| [picker](picker.md) | `val picker: `[`Picker`](../../com.lovoo.android.pickapp.model/-picker/index.md)<br>the Picker that will be observed |

### Functions

| Name | Summary |
|---|---|
| [destroy](destroy.md) | `fun destroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Call it when the ui is detached or destroyed. |
