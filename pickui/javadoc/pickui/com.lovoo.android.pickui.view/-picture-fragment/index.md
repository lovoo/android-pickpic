[pickui](../../index.md) / [com.lovoo.android.pickui.view](../index.md) / [PictureFragment](./index.md)

# PictureFragment

`class PictureFragment : Fragment, PictureView` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickui/pickui/src/main/kotlin/com/lovoo/android/pickui/view/PictureFragment.kt#L50)

Fragment that offers a predefined solution to load and present [Picture](#)s from a certain [Gallery](#).

**See Also**

[GalleryFragment](../-gallery-fragment/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PictureFragment()`<br>Fragment that offers a predefined solution to load and present [Picture](#)s from a certain [Gallery](#). |

### Functions

| Name | Summary |
|---|---|
| [getLifeCycle](get-life-cycle.md) | `fun getLifeCycle(): `[`PictureFragment`](./index.md) |
| [onAttach](on-attach.md) | `fun onAttach(context: Context): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?` |
| [onCursorLoaded](on-cursor-loaded.md) | `fun onCursorLoaded(cursor: Cursor?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDetach](on-detach.md) | `fun onDetach(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onViewCreated](on-view-created.md) | `fun onViewCreated(view: View, savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swap](swap.md) | `fun swap(gallery: Gallery): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Trigger that the Fragment will switch the current [Gallery](#) with the given one. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [TAG](-t-a-g.md) | `const val TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [newInstance](new-instance.md) | `fun newInstance(allowNestedScroll: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`PictureFragment`](./index.md) |
