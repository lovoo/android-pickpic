[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.contract](../index.md) / [FbPictureView](./index.md)

# FbPictureView

`interface FbPictureView` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/contract/FbPictureView.kt#L27)

Contract to implement for UI.

**See Also**

[com.lovoo.android.pickfacebook.view.FbPictureFragment](../../com.lovoo.android.pickfacebook.view/-fb-picture-fragment/index.md)

### Functions

| Name | Summary |
|---|---|
| [getLifeCycle](get-life-cycle.md) | `abstract fun getLifeCycle(): LifecycleOwner` |
| [onError](on-error.md) | `abstract fun onError(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when an error occurs. View should inform user. |
| [onPicturesLoaded](on-pictures-loaded.md) | `abstract fun onPicturesLoaded(pictures: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`>, galleryId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, next: GraphRequest?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when loading process is finished. |

### Inheritors

| Name | Summary |
|---|---|
| [FbPictureFragment](../../com.lovoo.android.pickfacebook.view/-fb-picture-fragment/index.md) | `class FbPictureFragment : Fragment, `[`FbPictureView`](./index.md)<br>Fragment that offers a predefined solution to load and present Facebook pictures from a certain [Gallery](#). |
