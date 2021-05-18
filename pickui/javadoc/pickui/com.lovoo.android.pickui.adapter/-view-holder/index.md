[pickui](../../index.md) / [com.lovoo.android.pickui.adapter](../index.md) / [ViewHolder](./index.md)

# ViewHolder

`abstract class ViewHolder<T> : ViewHolder` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickui/pickui/src/main/kotlin/com/lovoo/android/pickui/adapter/ViewHolder.kt#L40)

Typed [RecyclerView.ViewHolder](#) implementation that enforce its own width as height.

### Parameters

`view` - the current view for this [RecyclerView.ViewHolder](#)

`engine` - the [ImageEngine](#) to load pictures

`selectionLookUp` - determine if the item is selected

`onClick` - click callback for this item

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ViewHolder(view: View, engine: ImageEngine, selectionLookUp: (`[`T`](index.md#T)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, onClick: (View, `[`T`](index.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>Typed [RecyclerView.ViewHolder](#) implementation that enforce its own width as height. |

### Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | `fun bind(item: `[`T`](index.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set select state, click listener and load an image. |
| [bindCameraItem](bind-camera-item.md) | `fun bindCameraItem(cameraEngine: CameraEngine): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set display text an click listener for the camera button. |
| [getUri](get-uri.md) | `abstract fun getUri(item: `[`T`](index.md#T)`): Uri`<br>Determine the [Uri](#) that should be used for the [ImageEngine](#). |
| [recycle](recycle.md) | `fun recycle(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Reset the view. |
