[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.contract](../index.md) / [FbPicturePresenter](./index.md)

# FbPicturePresenter

`interface FbPicturePresenter` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/contract/FbPicturePresenter.kt#L12)

Contract to implement for Presenter.

**See Also**

[com.lovoo.android.pickfacebook.presenter.FbPicturePresenterImpl](../../com.lovoo.android.pickfacebook.presenter/-fb-picture-presenter-impl/index.md)

### Functions

| Name | Summary |
|---|---|
| [next](next.md) | `abstract fun next(gallery: Gallery, next: GraphRequest?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Load new pictures from Facebook. |
| [onActivityResult](on-activity-result.md) | `abstract fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onDestroy](on-destroy.md) | `abstract fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swap](swap.md) | `abstract fun swap(gallery: Gallery): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Switch to another [Gallery](#). |

### Inheritors

| Name | Summary |
|---|---|
| [FbPicturePresenterImpl](../../com.lovoo.android.pickfacebook.presenter/-fb-picture-presenter-impl/index.md) | `class FbPicturePresenterImpl : `[`FbPicturePresenter`](./index.md)<br>Implementation of [FbPicturePresenter](./index.md) to load the pictures from Facebook. Don't forget to forward onDestroy(). |
