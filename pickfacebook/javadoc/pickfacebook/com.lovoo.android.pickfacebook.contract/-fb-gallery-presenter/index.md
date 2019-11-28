[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.contract](../index.md) / [FbGalleryPresenter](./index.md)

# FbGalleryPresenter

`interface FbGalleryPresenter` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/contract/FbGalleryPresenter.kt#L13)

Contract to implement for Presenter.

**See Also**

[com.lovoo.android.pickfacebook.presenter.FbGalleryPresenterImpl](../../com.lovoo.android.pickfacebook.presenter/-fb-gallery-presenter-impl/index.md)

### Functions

| Name | Summary |
|---|---|
| [handleError](handle-error.md) | `abstract fun handleError(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when an error occurs. Presenter should clean up. |
| [isLoggedIn](is-logged-in.md) | `abstract fun isLoggedIn(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isPicturePermissionGranted](is-picture-permission-granted.md) | `abstract fun isPicturePermissionGranted(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [loadGalleries](load-galleries.md) | `abstract fun loadGalleries(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Request the Facebook galleries. Please remember to check for permissions beforehand. |
| [loginWithPicturePermission](login-with-picture-permission.md) | `abstract fun loginWithPicturePermission(fragment: Fragment): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>Start login process or update session to request picture permission. |
| [onActivityResult](on-activity-result.md) | `abstract fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onAttach](on-attach.md) | `abstract fun onAttach(activity: FragmentActivity): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.md) | `abstract fun onCreate(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDestroy](on-destroy.md) | `abstract fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [FbGalleryPresenterImpl](../../com.lovoo.android.pickfacebook.presenter/-fb-gallery-presenter-impl/index.md) | `class FbGalleryPresenterImpl : `[`FbGalleryPresenter`](./index.md)<br>Implementation of [FbGalleryPresenter](./index.md) for Fragments to load Facebook galleries. Don't forget to forward these events: onAttach() onCreate() onDestroy() |
