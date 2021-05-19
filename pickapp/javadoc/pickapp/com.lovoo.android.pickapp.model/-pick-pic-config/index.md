[pickapp](../../index.md) / [com.lovoo.android.pickapp.model](../index.md) / [PickPicConfig](./index.md)

# PickPicConfig

`data class PickPicConfig : Parcelable` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/pickapp/src/main/kotlin/com/lovoo/android/pickapp/model/PickPicConfig.kt#L38)

The configuration for [com.lovoo.android.pickapp.view.PickPicActivity](../../com.lovoo.android.pickapp.view/-pick-pic-activity/index.md) behaviour.

### Parameters

`style` - the style resource that should be used (control primary color and so on) or 0

`minCount` - the minimal count the user has to select before call to action is enabled, default 1

`maxCount` - the maximal count the user can select at all, default 1

`title` - the Activity title

`sendText` - the text for the call to action or null (see PickPicConfig.isPreviewEnabled)

`sendIcon` - the drawable resource identifier or null (see PickPicConfig.isPreviewEnabled)

`header` - the description below the Activity title or null if not wanted

`faqUrl` - the url that is used for the FAQ toolbar menu or null if not wanted

**See Also**

[com.lovoo.android.pickapp.factory.PickDependencies](../../com.lovoo.android.pickapp.factory/-pick-dependencies/index.md)

[com.lovoo.android.pickcore.PickPicProvider](#)

### Types

| Name | Summary |
|---|---|
| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : Creator<`[`PickPicConfig`](./index.md)`>` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PickPicConfig(parcel: Parcel)``PickPicConfig(style: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, minCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1, maxCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1, title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", sendText: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, sendIcon: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, header: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, faqUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null)`<br>The configuration for [com.lovoo.android.pickapp.view.PickPicActivity](../../com.lovoo.android.pickapp.view/-pick-pic-activity/index.md) behaviour. |

### Properties

| Name | Summary |
|---|---|
| [faqUrl](faq-url.md) | `val faqUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>the url that is used for the FAQ toolbar menu or null if not wanted |
| [header](header.md) | `val header: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>the description below the Activity title or null if not wanted |
| [maxCount](max-count.md) | `val maxCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>the maximal count the user can select at all, default 1 |
| [minCount](min-count.md) | `val minCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>the minimal count the user has to select before call to action is enabled, default 1 |
| [sendIcon](send-icon.md) | `val sendIcon: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?`<br>the drawable resource identifier or null (see PickPicConfig.isPreviewEnabled) |
| [sendText](send-text.md) | `val sendText: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>the text for the call to action or null (see PickPicConfig.isPreviewEnabled) |
| [style](style.md) | `val style: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>the style resource that should be used (control primary color and so on) or 0 |
| [title](title.md) | `val title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>the Activity title |

### Functions

| Name | Summary |
|---|---|
| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isPreviewEnabled](is-preview-enabled.md) | `fun isPreviewEnabled(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Used to define if preview and selectionbar has to be initialized. If this is true and the user hit the selection maxCount the picker will be finished. |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: Parcel, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: Parcel): `[`PickPicConfig`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`PickPicConfig`](./index.md)`?>` |
