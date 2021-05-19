[pickfacebook](../../index.md) / [com.lovoo.android.pickfacebook.view](../index.md) / [FbGalleryFragment](./index.md)

# FbGalleryFragment

`class FbGalleryFragment : Fragment, `[`FbGalleryView`](../../com.lovoo.android.pickfacebook.contract/-fb-gallery-view/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickfacebook/pickfacebook/src/main/kotlin/com/lovoo/android/pickfacebook/view/FbGalleryFragment.kt#L46)

Fragment that offers a predefined solution to load and present Facebook [Gallery](#) and there
containing pictures. It also request the Facebook user-photos Permission.

**See Also**

[FbGalleryFragment.newInstance](new-instance.md)

[com.lovoo.android.pickui.view.GalleryFragment](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FbGalleryFragment()`<br>Fragment that offers a predefined solution to load and present Facebook [Gallery](#) and there containing pictures. It also request the Facebook user-photos Permission. |

### Functions

| Name | Summary |
|---|---|
| [getLifeCycle](get-life-cycle.md) | `fun getLifeCycle(): `[`FbGalleryFragment`](./index.md) |
| [onAccessTokenChanged](on-access-token-changed.md) | `fun onAccessTokenChanged(token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when a Facebook session was created or updated. |
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAttach](on-attach.md) | `fun onAttach(context: Context): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?` |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onError](on-error.md) | `fun onError(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when an error occurs. View should inform user. |
| [onGalleriesLoaded](on-galleries-loaded.md) | `fun onGalleriesLoaded(galleries: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Gallery>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when loading process is finished. |
| [onSaveInstanceState](on-save-instance-state.md) | `fun onSaveInstanceState(outState: Bundle): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onUserChanged](on-user-changed.md) | `fun onUserChanged(user: `[`Facebook.User`](../../com.lovoo.android.pickfacebook/-facebook/-user/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when a Facebook user was loaded or updated. |
| [onViewCreated](on-view-created.md) | `fun onViewCreated(view: View, savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setMenuVisibility](set-menu-visibility.md) | `fun setMenuVisibility(menuVisible: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [newInstance](new-instance.md) | `fun newInstance(allowNestedScroll: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`FbGalleryFragment`](./index.md) |
