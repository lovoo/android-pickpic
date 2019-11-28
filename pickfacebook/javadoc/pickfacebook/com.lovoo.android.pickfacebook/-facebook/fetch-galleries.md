[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook](../index.md) / [Facebook](index.md) / [fetchGalleries](./fetch-galleries.md)

# fetchGalleries

`fun fetchGalleries(callback: (`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Gallery>, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/Facebook.kt#L88)

Fetch  [Gallery](#)s from Facebook. Multiple requests will be executed
until the whole list is loaded.

### Parameters

`callback` - the callback to receive the complete list or error