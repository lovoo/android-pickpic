[pickui](../../index.md) / [com.lovoo.android.pickui.adapter](../index.md) / [GalleryAdapter](./index.md)

# GalleryAdapter

`class GalleryAdapter : RecyclerViewCursorAdapter<ViewHolder>` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickui/pickui/src/main/kotlin/com/lovoo/android/pickui/adapter/GalleryAdapter.kt#L43)

RecyclerView Adapter to present [Gallery](#)s.

### Parameters

`context` - app context

`allFolderName` - the display name for the all Folder entry

`onClick` - the callback when an entry was clicked

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GalleryAdapter(context: Context, allFolderName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, onClick: (View, Gallery) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>RecyclerView Adapter to present [Gallery](#)s. |

### Functions

| Name | Summary |
|---|---|
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: ViewHolder, cursor: Cursor): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ViewHolder` |
