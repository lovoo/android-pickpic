[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook](../index.md) / [Facebook](./index.md)

# Facebook

`class Facebook` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/Facebook.kt#L18)

Implementation of the Facebook SDK.

### Types

| Name | Summary |
|---|---|
| [User](-user/index.md) | `data class User` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Facebook()`<br>Implementation of the Facebook SDK. |

### Properties

| Name | Summary |
|---|---|
| [accessToken](access-token.md) | `val accessToken: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>`<br>Observable to receive new access token from Facebook or null. |
| [user](user.md) | `val user: MutableLiveData<`[`Facebook.User`](-user/index.md)`?>`<br>Observable to receive new [User](-user/index.md) from Facebook |

### Functions

| Name | Summary |
|---|---|
| [clearSession](clear-session.md) | `fun clearSession(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Logout from Facebook. |
| [destroy](destroy.md) | `fun destroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Cleanup listener. |
| [fetchCurrentProfile](fetch-current-profile.md) | `fun fetchCurrentProfile(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Fetch [User](-user/index.md) from Facebook. |
| [fetchGalleries](fetch-galleries.md) | `fun fetchGalleries(callback: (`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Gallery>, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Fetch  [Gallery](#)s from Facebook. Multiple requests will be executed until the whole list is loaded. |
| [fetchPictures](fetch-pictures.md) | `fun fetchPictures(next: GraphRequest?, albumId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, callback: (`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`>, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?, albumId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, next: GraphRequest?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Fetch [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)s from Facebook. |
| [getDeclinedPermissions](get-declined-permissions.md) | `fun getDeclinedPermissions(): `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>?` |
| [getPermissions](get-permissions.md) | `fun getPermissions(): `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>?` |
| [isExpired](is-expired.md) | `fun isExpired(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isLoggedIn](is-logged-in.md) | `fun isLoggedIn(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Forward the data to Facebook SDK. |
| [requestAccessToken](request-access-token.md) | `fun requestAccessToken(fragment: Fragment, permissions: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = listOf("public_profile"), callback: FacebookCallback<LoginResult>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Start Login process and request access token. |
