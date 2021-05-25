[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.view](../index.md) / [FbPictureFragment](index.md) / [onPicturesLoaded](./on-pictures-loaded.md)

# onPicturesLoaded

`fun onPicturesLoaded(pictures: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`>, galleryId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, next: GraphRequest?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/view/FbPictureFragment.kt#L121)

Overrides [FbPictureView.onPicturesLoaded](../../com.lovoo.android.pickfacebook.contract/-fb-picture-view/on-pictures-loaded.md)

Called when loading process is finished.

### Parameters

`pictures` - the list of loaded [FbPicture](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)

`galleryId` - the parent [com.lovoo.android.pickcore.model.Gallery](#) id

`next` - the request to load the next page of pictures or null