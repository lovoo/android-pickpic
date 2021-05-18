[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook](../index.md) / [Facebook](index.md) / [requestAccessToken](./request-access-token.md)

# requestAccessToken

`fun requestAccessToken(fragment: Fragment, permissions: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = listOf("public_profile"), callback: FacebookCallback<LoginResult>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/Facebook.kt#L86)

Start Login process and request access token.

### Parameters

`fragment` - the [Fragment](#) that should receive the onActivityResult call

`permissions` - list of permissions to request from Facebook

`callback` - the callback to handle the result