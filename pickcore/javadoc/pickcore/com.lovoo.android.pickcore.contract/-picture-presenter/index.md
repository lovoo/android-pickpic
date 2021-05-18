[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [PicturePresenter](./index.md)

# PicturePresenter

`interface PicturePresenter` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/PicturePresenter.kt#L26)

Contract to implement for Presenter.

**See Also**

[com.lovoo.android.pickcore.presenter.PicturePresenterImpl](../../com.lovoo.android.pickcore.presenter/-picture-presenter-impl/index.md)

### Functions

| Name | Summary |
|---|---|
| [onDestroy](on-destroy.md) | `abstract fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Notify that the Fragment got destroyed. |
| [swap](swap.md) | `abstract fun swap(activity: FragmentActivity, gallery: `[`Gallery`](../../com.lovoo.android.pickcore.model/-gallery/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Trigger cursor loading for given [Gallery](../../com.lovoo.android.pickcore.model/-gallery/index.md) and destroy the last loaded cursor. |

### Inheritors

| Name | Summary |
|---|---|
| [PicturePresenterImpl](../../com.lovoo.android.pickcore.presenter/-picture-presenter-impl/index.md) | `class PicturePresenterImpl : `[`PicturePresenter`](./index.md)<br>Implementation of [PicturePresenter](./index.md) for Fragments to load the pictures from a [Gallery](../../com.lovoo.android.pickcore.model/-gallery/index.md). Don't forget to forward onDestroy(). |
