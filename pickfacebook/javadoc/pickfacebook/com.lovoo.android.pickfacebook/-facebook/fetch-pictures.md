[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook](../index.md) / [Facebook](index.md) / [fetchPictures](./fetch-pictures.md)

# fetchPictures

`fun fetchPictures(next: GraphRequest?, albumId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, callback: (`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`>, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?, albumId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, next: GraphRequest?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/Facebook.kt#L132)

Fetch [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)s from Facebook.

### Parameters

`next` - the [GraphRequest](#) for the next page or null for init request

`albumId` - the Identifier of the requested album

`callback` - the callback to receive the loaded list or error together with the request for the next page