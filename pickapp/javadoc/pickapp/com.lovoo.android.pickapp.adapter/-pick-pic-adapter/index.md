[pickapp](../../index.md) / [com.lovoo.android.pickapp.adapter](../index.md) / [PickPicAdapter](./index.md)

# PickPicAdapter

`class PickPicAdapter : FragmentStatePagerAdapter` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/pickapp/src/main/kotlin/com/lovoo/android/pickapp/adapter/PickPicAdapter.kt#L34)

[FragmentStatePagerAdapter](#) that contains [GalleryFragment](#) and optional [FbGalleryFragment](#).

### Parameters

`context` - the app context

`manager` - the [FragmentManager](#) that should be used

`dependencies` - [PickDependencies](../../com.lovoo.android.pickapp.factory/-pick-dependencies/index.md) instance to decide if [FbGalleryFragment](#) should be added or not

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PickPicAdapter(context: Context, manager: FragmentManager, dependencies: `[`PickDependencies`](../../com.lovoo.android.pickapp.factory/-pick-dependencies/index.md)`)`<br>[FragmentStatePagerAdapter](#) that contains [GalleryFragment](#) and optional [FbGalleryFragment](#). |

### Functions

| Name | Summary |
|---|---|
| [getCount](get-count.md) | `fun getCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItem](get-item.md) | `fun getItem(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Fragment` |
| [getPageTitle](get-page-title.md) | `fun getPageTitle(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
