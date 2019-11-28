[pickapp](../../index.md) / [com.lovoo.android.pickapp.model](../index.md) / [PickPicConfig](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`PickPicConfig(parcel: Parcel)``PickPicConfig(@StyleRes style: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, minCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1, maxCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1, title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", sendText: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, @DrawableRes sendIcon: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, header: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, faqUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null)`

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

