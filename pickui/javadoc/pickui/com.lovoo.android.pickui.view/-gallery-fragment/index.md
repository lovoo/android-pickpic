[pickui](../../index.md) / [com.lovoo.android.pickui.view](../index.md) / [GalleryFragment](./index.md)

# GalleryFragment

`class GalleryFragment : Fragment, GalleryView` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickui/src/main/kotlin/com/lovoo/android/pickui/view/GalleryFragment.kt#L37)

Fragment that offers a predefined solution to load and present [Gallery](#)s and there
containing pictures. It also request the Android Storage Permission.

**See Also**

[GalleryFragment.newInstance](new-instance.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GalleryFragment()`<br>Fragment that offers a predefined solution to load and present [Gallery](#)s and there containing pictures. It also request the Android Storage Permission. |

### Functions

| Name | Summary |
|---|---|
| [getLifeCycle](get-life-cycle.md) | `fun getLifeCycle(): `[`GalleryFragment`](./index.md) |
| [onAttach](on-attach.md) | `fun onAttach(context: Context?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?` |
| [onCursorLoaded](on-cursor-loaded.md) | `fun onCursorLoaded(cursor: Cursor?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDetach](on-detach.md) | `fun onDetach(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onResume](on-resume.md) | `fun onResume(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSaveInstanceState](on-save-instance-state.md) | `fun onSaveInstanceState(outState: Bundle): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onViewCreated](on-view-created.md) | `fun onViewCreated(view: View, savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [newInstance](new-instance.md) | `fun newInstance(): `[`GalleryFragment`](./index.md) |
