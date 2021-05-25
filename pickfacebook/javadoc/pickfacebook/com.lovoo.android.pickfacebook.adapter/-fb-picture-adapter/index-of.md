[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.adapter](../index.md) / [FbPictureAdapter](index.md) / [indexOf](./index-of.md)

# indexOf

`fun indexOf(filter: (`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/adapter/FbPictureAdapter.kt#L78)

### Parameters

`filter` - Filter that should return true if given [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md) was searched

**Return**
index of first [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md) that returns true from passed filter or -1 if not found

