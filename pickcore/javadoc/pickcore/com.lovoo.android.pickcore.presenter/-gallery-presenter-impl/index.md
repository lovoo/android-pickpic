[pickcore](../../index.md) / [com.lovoo.android.pickcore.presenter](../index.md) / [GalleryPresenterImpl](./index.md)

# GalleryPresenterImpl

`class GalleryPresenterImpl : `[`GalleryPresenter`](../../com.lovoo.android.pickcore.contract/-gallery-presenter/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/presenter/GalleryPresenterImpl.kt#L23)

Implementation of [GalleryPresenter](../../com.lovoo.android.pickcore.contract/-gallery-presenter/index.md) for Fragments to load the device galleries.
Don't forget to forward these events:
onAttach()
onCreate()
onDestroy()

Start loading with load() if you have the permission to access the Filesystem.

### Parameters

`view` - the contract to the UI Layer

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GalleryPresenterImpl(view: `[`GalleryView`](../../com.lovoo.android.pickcore.contract/-gallery-view/index.md)`)`<br>Implementation of [GalleryPresenter](../../com.lovoo.android.pickcore.contract/-gallery-presenter/index.md) for Fragments to load the device galleries. Don't forget to forward these events: onAttach() onCreate() onDestroy() |

### Functions

| Name | Summary |
|---|---|
| [load](load.md) | `fun load(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Start the loading process if onAttach was called already. |
| [onAttach](on-attach.md) | `fun onAttach(activity: FragmentActivity): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the last [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md) and create a new [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md) to load the device galleries. Result will be forwarded in [GalleryView](../../com.lovoo.android.pickcore.contract/-gallery-view/index.md).onCursorLoaded([Cursor](#)?). |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Currently empty implemented. |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the current [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md). |
