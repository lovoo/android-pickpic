[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.contract](../index.md) / [FbGalleryView](./index.md)

# FbGalleryView

`interface FbGalleryView` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/contract/FbGalleryView.kt#L27)

Contract to implement for UI.

**See Also**

[com.lovoo.android.pickfacebook.view.FbGalleryFragment](../../com.lovoo.android.pickfacebook.view/-fb-gallery-fragment/index.md)

### Functions

| Name | Summary |
|---|---|
| [getLifeCycle](get-life-cycle.md) | `abstract fun getLifeCycle(): LifecycleOwner` |
| [onAccessTokenChanged](on-access-token-changed.md) | `abstract fun onAccessTokenChanged(token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when a Facebook session was created or updated. |
| [onError](on-error.md) | `abstract fun onError(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when an error occurs. View should inform user. |
| [onGalleriesLoaded](on-galleries-loaded.md) | `abstract fun onGalleriesLoaded(galleries: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Gallery>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when loading process is finished. |
| [onUserChanged](on-user-changed.md) | `abstract fun onUserChanged(user: `[`Facebook.User`](../../com.lovoo.android.pickfacebook/-facebook/-user/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when a Facebook user was loaded or updated. |

### Inheritors

| Name | Summary |
|---|---|
| [FbGalleryFragment](../../com.lovoo.android.pickfacebook.view/-fb-gallery-fragment/index.md) | `class FbGalleryFragment : Fragment, `[`FbGalleryView`](./index.md)<br>Fragment that offers a predefined solution to load and present Facebook [Gallery](#) and there containing pictures. It also request the Facebook user-photos Permission. |
