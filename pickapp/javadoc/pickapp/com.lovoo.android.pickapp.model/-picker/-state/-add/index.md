[pickapp](../../../../index.md) / [com.lovoo.android.pickapp.model](../../../index.md) / [Picker](../../index.md) / [State](../index.md) / [Add](./index.md)

# Add

`data class Add : `[`Picker.State`](../index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/pickapp/src/main/kotlin/com/lovoo/android/pickapp/model/Picker.kt#L160)

State that notifies that a new item was picked.

### Parameters

`uri` - the [Uri](#)

`gallery` - the [Gallery](#) of the picture

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Add(uri: Uri, gallery: Gallery)`<br>State that notifies that a new item was picked. |

### Properties

| Name | Summary |
|---|---|
| [gallery](gallery.md) | `val gallery: Gallery`<br>the [Gallery](#) of the picture |
| [uri](uri.md) | `val uri: Uri`<br>the [Uri](#) |
