[pickfacebook](../../../index.md) / [com.lovoo.android.pickfacebook.adapter](../../index.md) / [FbGalleryAdapter](../index.md) / [ViewHolder](./index.md)

# ViewHolder

`class ViewHolder : ViewHolder` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/adapter/FbGalleryAdapter.kt#L61)

[RecyclerView.ViewHolder](#) implementation that enforce its own width as height.

### Parameters

`view` - the current view for this [RecyclerView.ViewHolder](#)

`engine` - the [ImageEngine](#) to load pictures

`onClick` - click callback for this item

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ViewHolder(view: View, engine: ImageEngine, onClick: (View, Gallery) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>[RecyclerView.ViewHolder](#) implementation that enforce its own width as height. |

### Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | `fun bind(gallery: Gallery): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
