[pickapp](../../../index.md) / [com.lovoo.android.pickapp.model](../../index.md) / [Picker](../index.md) / [AddState](./index.md)

# AddState

`data class AddState : `[`Picker.State`](../-state/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/model/Picker.kt#L147)

State that notifies that a new item was picked.

### Parameters

`uri` - the [Uri](#)

`gallery` - the [Gallery](#) of the picture

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AddState(uri: Uri, gallery: Gallery)`<br>State that notifies that a new item was picked. |

### Properties

| Name | Summary |
|---|---|
| [gallery](gallery.md) | `val gallery: Gallery`<br>the [Gallery](#) of the picture |
| [uri](uri.md) | `val uri: Uri`<br>the [Uri](#) |
