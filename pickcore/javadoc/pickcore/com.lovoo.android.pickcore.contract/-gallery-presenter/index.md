[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [GalleryPresenter](./index.md)

# GalleryPresenter

`interface GalleryPresenter` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/GalleryPresenter.kt#L26)

Contract to implement for Presenter.

**See Also**

[com.lovoo.android.pickcore.presenter.GalleryPresenterImpl](../../com.lovoo.android.pickcore.presenter/-gallery-presenter-impl/index.md)

### Functions

| Name | Summary |
|---|---|
| [load](load.md) | `abstract fun load(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Start to load all device galleries. |
| [onAttach](on-attach.md) | `abstract fun onAttach(activity: FragmentActivity): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Notify that the Fragment got attached. |
| [onCreate](on-create.md) | `abstract fun onCreate(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Notify that the Fragment is created or restored. |
| [onDestroy](on-destroy.md) | `abstract fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Notify that the Fragment got destroyed. |

### Inheritors

| Name | Summary |
|---|---|
| [GalleryPresenterImpl](../../com.lovoo.android.pickcore.presenter/-gallery-presenter-impl/index.md) | `class GalleryPresenterImpl : `[`GalleryPresenter`](./index.md)<br>Implementation of [GalleryPresenter](./index.md) for Fragments to load the device galleries. Don't forget to forward these events: onAttach() onCreate() onDestroy() |
