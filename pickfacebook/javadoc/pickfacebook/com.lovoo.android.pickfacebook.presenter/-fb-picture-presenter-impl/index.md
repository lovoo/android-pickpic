[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.presenter](../index.md) / [FbPicturePresenterImpl](./index.md)

# FbPicturePresenterImpl

`class FbPicturePresenterImpl : `[`FbPicturePresenter`](../../com.lovoo.android.pickfacebook.contract/-fb-picture-presenter/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/presenter/FbPicturePresenterImpl.kt#L33)

Implementation of [FbPicturePresenter](../../com.lovoo.android.pickfacebook.contract/-fb-picture-presenter/index.md) to load the pictures from Facebook.
Don't forget to forward onDestroy().

Start loading with swap().

### Parameters

`view` - the contract to the UI Layer

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FbPicturePresenterImpl(view: `[`FbPictureView`](../../com.lovoo.android.pickfacebook.contract/-fb-picture-view/index.md)`)`<br>Implementation of [FbPicturePresenter](../../com.lovoo.android.pickfacebook.contract/-fb-picture-presenter/index.md) to load the pictures from Facebook. Don't forget to forward onDestroy(). |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `val view: `[`FbPictureView`](../../com.lovoo.android.pickfacebook.contract/-fb-picture-view/index.md)<br>the contract to the UI Layer |

### Functions

| Name | Summary |
|---|---|
| [next](next.md) | `fun next(gallery: Gallery, next: GraphRequest?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load pictures from a Facebook [Gallery](#) from [GraphRequest](#). |
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swap](swap.md) | `fun swap(gallery: Gallery): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load pictures from a Facebook [Gallery](#) from start. |
