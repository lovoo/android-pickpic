[pickapp](../../../../index.md) / [com.lovoo.android.pickapp.model](../../../index.md) / [Picker](../../index.md) / [State](../index.md) / [Remove](./index.md)

# Remove

`data class Remove : `[`Picker.State`](../index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/pickapp/src/main/kotlin/com/lovoo/android/pickapp/model/Picker.kt#L171)

State that notifies that a picked item was removed.

### Parameters

`uri` - the [Uri](#)

`gallery` - the [Gallery](#) of the picture

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Remove(uri: Uri, gallery: Gallery)`<br>State that notifies that a picked item was removed. |

### Properties

| Name | Summary |
|---|---|
| [gallery](gallery.md) | `val gallery: Gallery`<br>the [Gallery](#) of the picture |
| [uri](uri.md) | `val uri: Uri`<br>the [Uri](#) |
