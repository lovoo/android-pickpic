[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.view](../index.md) / [FbPictureFragment](./index.md)

# FbPictureFragment

`class FbPictureFragment : Fragment, `[`FbPictureView`](../../com.lovoo.android.pickfacebook.contract/-fb-picture-view/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/view/FbPictureFragment.kt#L34)

Fragment that offers a predefined solution to load and present Facebook pictures from a certain [Gallery](#).

**See Also**

[FbGalleryFragment](../-fb-gallery-fragment/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FbPictureFragment()`<br>Fragment that offers a predefined solution to load and present Facebook pictures from a certain [Gallery](#). |

### Functions

| Name | Summary |
|---|---|
| [getLifeCycle](get-life-cycle.md) | `fun getLifeCycle(): `[`FbPictureFragment`](./index.md) |
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAttach](on-attach.md) | `fun onAttach(context: Context?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?` |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDetach](on-detach.md) | `fun onDetach(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onError](on-error.md) | `fun onError(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when an error occurs. View should inform user. |
| [onPicturesLoaded](on-pictures-loaded.md) | `fun onPicturesLoaded(pictures: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FbPicture`](../../com.lovoo.android.pickfacebook.model/-fb-picture/index.md)`>, galleryId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, next: GraphRequest?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when loading process is finished. |
| [onViewCreated](on-view-created.md) | `fun onViewCreated(view: View, savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swap](swap.md) | `fun swap(gallery: Gallery): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Trigger that the Fragment will switch the current [Gallery](#) with the given one. |
