[pickcore](../../index.md) / [com.lovoo.android.pickcore.contract](../index.md) / [PicturePresenter](index.md) / [swap](./swap.md)

# swap

`abstract fun swap(activity: FragmentActivity, gallery: `[`Gallery`](../../com.lovoo.android.pickcore.model/-gallery/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/pickcore/src/main/kotlin/com/lovoo/android/pickcore/contract/PicturePresenter.kt#L34)

Trigger cursor loading for given [Gallery](../../com.lovoo.android.pickcore.model/-gallery/index.md) and destroy the last loaded cursor.

### Parameters

`activity` - the current [FragmentActivity](#)

`gallery` - the [Gallery](../../com.lovoo.android.pickcore.model/-gallery/index.md) that should be used now