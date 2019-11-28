[pickapp](../../index.md) / [com.lovoo.android.pickapp.view](../index.md) / [ReverseLayoutManager](./index.md)

# ReverseLayoutManager

`open class ReverseLayoutManager : LinearLayoutManager` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/view/ReverseLayoutManager.kt#L12)

Custom LinearLayoutManager that triggers scroll to position 0 on view added
when it is enabled.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ReverseLayoutManager(context: Context)`<br>`ReverseLayoutManager(context: Context, orientation: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, reverseLayout: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`<br>`ReverseLayoutManager(context: Context, attrs: AttributeSet, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, defStyleRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [scrollEnabled](scroll-enabled.md) | `var scrollEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Set to false if you want to disable the auto scroll. |

### Functions

| Name | Summary |
|---|---|
| [addView](add-view.md) | `open fun addView(child: View?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
