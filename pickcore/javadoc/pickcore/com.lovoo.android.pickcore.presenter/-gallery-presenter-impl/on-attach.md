[pickcore](../../index.md) / [com.lovoo.android.pickcore.presenter](../index.md) / [GalleryPresenterImpl](index.md) / [onAttach](./on-attach.md)

# onAttach

`fun onAttach(activity: FragmentActivity): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/presenter/GalleryPresenterImpl.kt#L34)

Overrides [GalleryPresenter.onAttach](../../com.lovoo.android.pickcore.contract/-gallery-presenter/on-attach.md)

Clear the last [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md) and create a new [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md) to load the device galleries.
Result will be forwarded in [GalleryView](../../com.lovoo.android.pickcore.contract/-gallery-view/index.md).onCursorLoaded([Cursor](#)?).

### Parameters

`activity` - the current [FragmentActivity](#)