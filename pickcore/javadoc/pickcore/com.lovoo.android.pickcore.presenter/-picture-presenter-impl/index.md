[pickcore](../../index.md) / [com.lovoo.android.pickcore.presenter](../index.md) / [PicturePresenterImpl](./index.md)

# PicturePresenterImpl

`class PicturePresenterImpl : `[`PicturePresenter`](../../com.lovoo.android.pickcore.contract/-picture-presenter/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/presenter/PicturePresenterImpl.kt#L21)

Implementation of [PicturePresenter](../../com.lovoo.android.pickcore.contract/-picture-presenter/index.md) for Fragments to load the pictures from a [Gallery](../../com.lovoo.android.pickcore.model/-gallery/index.md).
Don't forget to forward onDestroy().

Start loading with swap().

### Parameters

`view` - the contract to the UI Layer

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PicturePresenterImpl(view: `[`PictureView`](../../com.lovoo.android.pickcore.contract/-picture-view/index.md)`)`<br>Implementation of [PicturePresenter](../../com.lovoo.android.pickcore.contract/-picture-presenter/index.md) for Fragments to load the pictures from a [Gallery](../../com.lovoo.android.pickcore.model/-gallery/index.md). Don't forget to forward onDestroy(). |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `val view: `[`PictureView`](../../com.lovoo.android.pickcore.contract/-picture-view/index.md)<br>the contract to the UI Layer |

### Functions

| Name | Summary |
|---|---|
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the current [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md). |
| [swap](swap.md) | `fun swap(activity: FragmentActivity, gallery: `[`Gallery`](../../com.lovoo.android.pickcore.model/-gallery/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the last [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md) and and creates a new [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md) to load the device pictures from gallery. Result will be forwarded in [PictureView](../../com.lovoo.android.pickcore.contract/-picture-view/index.md).onCursorLoaded([Cursor](#)?). |
