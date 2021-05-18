[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.adapter](../index.md) / [FbGalleryAdapter](./index.md)

# FbGalleryAdapter

`class FbGalleryAdapter : Adapter<`[`FbGalleryAdapter.ViewHolder`](-view-holder/index.md)`>` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/adapter/FbGalleryAdapter.kt#L37)

RecyclerView Adapter to present [Gallery](#)s.

### Parameters

`context` - app context

`onClick` - the callback when an entry was clicked

### Types

| Name | Summary |
|---|---|
| [ViewHolder](-view-holder/index.md) | `class ViewHolder : ViewHolder`<br>[RecyclerView.ViewHolder](#) implementation that enforce its own width as height. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FbGalleryAdapter(context: Context, onClick: (View, Gallery) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>RecyclerView Adapter to present [Gallery](#)s. |

### Properties

| Name | Summary |
|---|---|
| [galleries](galleries.md) | `var galleries: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Gallery>`<br>Non mutable list of [Gallery](#)s. Every set will notify adapter. |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`FbGalleryAdapter.ViewHolder`](-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`FbGalleryAdapter.ViewHolder`](-view-holder/index.md) |
