[pickcore](../../index.md) / [com.lovoo.android.pickcore.presenter](../index.md) / [PicturePresenterImpl](index.md) / [swap](./swap.md)

# swap

`fun swap(activity: FragmentActivity, gallery: `[`Gallery`](../../com.lovoo.android.pickcore.model/-gallery/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/presenter/PicturePresenterImpl.kt#L47)

Overrides [PicturePresenter.swap](../../com.lovoo.android.pickcore.contract/-picture-presenter/swap.md)

Clear the last [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md) and and creates a new [Collector](../../com.lovoo.android.pickcore.loader/-collector/index.md) to load the device pictures from gallery.
Result will be forwarded in [PictureView](../../com.lovoo.android.pickcore.contract/-picture-view/index.md).onCursorLoaded([Cursor](#)?).

### Parameters

`activity` - the current [FragmentActivity](#)

`gallery` - the current selected [Gallery](../../com.lovoo.android.pickcore.model/-gallery/index.md)