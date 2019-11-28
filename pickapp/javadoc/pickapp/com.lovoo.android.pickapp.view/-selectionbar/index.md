[pickapp](../../index.md) / [com.lovoo.android.pickapp.view](../index.md) / [Selectionbar](./index.md)

# Selectionbar

`class Selectionbar` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/view/Selectionbar.kt#L36)

UI extension to handle the PickPicActivities Selectionbar and RecyclerView.
It observes changes emitted by [Picker](../../com.lovoo.android.pickapp.model/-picker/index.md) and add, removes or select the Thumbnails.
You have to call destroy if the UI is removed.

The action button text and click handle is customizable via setters.

### Parameters

`picker` - the Picker instance

`layout` - the bottom sheet layout.

`dependingViews` - the views that should dodge the sheet

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Selectionbar(picker: `[`Picker`](../../com.lovoo.android.pickapp.model/-picker/index.md)`, layout: View, dependingViews: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<View>)`<br>UI extension to handle the PickPicActivities Selectionbar and RecyclerView. It observes changes emitted by [Picker](../../com.lovoo.android.pickapp.model/-picker/index.md) and add, removes or select the Thumbnails. You have to call destroy if the UI is removed. |

### Functions

| Name | Summary |
|---|---|
| [destroy](destroy.md) | `fun destroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Call it when the ui is detached or destroyed. |
| [setButtonIcon](set-button-icon.md) | `fun setButtonIcon(icon: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setButtonOnClick](set-button-on-click.md) | `fun setButtonOnClick(listener: (() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the action for the bottom bar done button. |
| [setButtonText](set-button-text.md) | `fun setButtonText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
