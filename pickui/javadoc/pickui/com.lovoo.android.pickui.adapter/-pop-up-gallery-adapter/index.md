[pickui](../../index.md) / [com.lovoo.android.pickui.adapter](../index.md) / [PopUpGalleryAdapter](./index.md)

# PopUpGalleryAdapter

`class PopUpGalleryAdapter : BaseAdapter` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickui/src/main/kotlin/com/lovoo/android/pickui/adapter/PopUpGalleryAdapter.kt#L25)

Adapter for ListView or PopUpWindows.
Create preview layout for [Gallery](#)s with a cover image,
display name and the amount of containing pictures.

### Parameters

`context` - the app context

`items` - the list of [Gallery](#)s

`folderNameLookUp` - determine the display name for a [Gallery](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PopUpGalleryAdapter(context: Context, items: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Gallery>, folderNameLookUp: (Gallery) -> `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>Adapter for ListView or PopUpWindows. Create preview layout for [Gallery](#)s with a cover image, display name and the amount of containing pictures. |

### Functions

| Name | Summary |
|---|---|
| [getCount](get-count.md) | `fun getCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItem](get-item.md) | `fun getItem(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Gallery?` |
| [getItemId](get-item-id.md) | `fun getItemId(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getView](get-view.md) | `fun getView(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, convertView: View?, container: ViewGroup?): View` |
