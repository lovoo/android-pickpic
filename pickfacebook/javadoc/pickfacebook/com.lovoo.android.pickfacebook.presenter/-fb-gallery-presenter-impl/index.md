[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.presenter](../index.md) / [FbGalleryPresenterImpl](./index.md)

# FbGalleryPresenterImpl

`class FbGalleryPresenterImpl : `[`FbGalleryPresenter`](../../com.lovoo.android.pickfacebook.contract/-fb-gallery-presenter/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/presenter/FbGalleryPresenterImpl.kt#L41)

Implementation of [FbGalleryPresenter](../../com.lovoo.android.pickfacebook.contract/-fb-gallery-presenter/index.md) for Fragments to load Facebook galleries.
Don't forget to forward these events:
onAttach()
onCreate()
onDestroy()

Start loading with loadGalleries() if you have the permission to access the Images.

### Parameters

`view` - the contract to the UI Layer

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FbGalleryPresenterImpl(view: `[`FbGalleryView`](../../com.lovoo.android.pickfacebook.contract/-fb-gallery-view/index.md)`)`<br>Implementation of [FbGalleryPresenter](../../com.lovoo.android.pickfacebook.contract/-fb-gallery-presenter/index.md) for Fragments to load Facebook galleries. Don't forget to forward these events: onAttach() onCreate() onDestroy() |

### Functions

| Name | Summary |
|---|---|
| [handleError](handle-error.md) | `fun handleError(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the Facebook session. |
| [isLoggedIn](is-logged-in.md) | `fun isLoggedIn(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isPicturePermissionGranted](is-picture-permission-granted.md) | `fun isPicturePermissionGranted(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [loadGalleries](load-galleries.md) | `fun loadGalleries(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load list of [com.lovoo.android.pickcore.model.Gallery](#). |
| [loginWithPicturePermission](login-with-picture-permission.md) | `fun loginWithPicturePermission(fragment: Fragment): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Try to load new access token with picture permission from Facebook. |
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onAttach](on-attach.md) | `fun onAttach(activity: FragmentActivity): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
