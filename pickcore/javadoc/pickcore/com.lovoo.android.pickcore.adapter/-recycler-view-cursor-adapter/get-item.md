[pickcore](../../index.md) / [com.lovoo.android.pickcore.adapter](../index.md) / [RecyclerViewCursorAdapter](index.md) / [getItem](./get-item.md)

# getItem

`fun <T> getItem(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, transform: (Cursor) -> `[`T`](get-item.md#T)`): `[`T`](get-item.md#T)`?` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/adapter/RecyclerViewCursorAdapter.kt#L77)

### Parameters

`position` - the requested position

`transform` - the transformation to convert the [Cursor](#) entry to a model class

**Return**
the transformed model for the [Cursor](#) item at this position or null

