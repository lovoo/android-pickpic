[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.contract](../index.md) / [FbPictureView](index.md) / [onPicturesLoaded](./on-pictures-loaded.md)

# onPicturesLoaded

`abstract fun onPicturesLoaded(pictures: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`>, galleryId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, next: GraphRequest?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/contract/FbPictureView.kt#L26)

Called when loading process is finished.

### Parameters

`pictures` - the list of loaded [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)

`galleryId` - the parent [com.lovoo.android.pickcore.model.Gallery](#) id

`next` - the request to load the next page of pictures or null