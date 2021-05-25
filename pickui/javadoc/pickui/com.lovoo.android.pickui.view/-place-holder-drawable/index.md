[pickui](../../index.md) / [com.lovoo.android.pickui.view](../index.md) / [PlaceHolderDrawable](./index.md)

# PlaceHolderDrawable

`class PlaceHolderDrawable : Drawable` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickui/pickui/src/main/kotlin/com/lovoo/android/pickui/view/PlaceHolderDrawable.kt#L30)

Custom Drawable that is used as translucent placeholder.
In comparison to other Drawables this one can get
width and height to measure its dimensions.

It draws nothing therefor aplha channel and color filter are ignored.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PlaceHolderDrawable()`<br>Custom Drawable that is used as translucent placeholder. In comparison to other Drawables this one can get width and height to measure its dimensions. |

### Properties

| Name | Summary |
|---|---|
| [height](height.md) | `var height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [width](width.md) | `var width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [draw](draw.md) | `fun draw(canvas: Canvas): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getIntrinsicHeight](get-intrinsic-height.md) | `fun getIntrinsicHeight(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getIntrinsicWidth](get-intrinsic-width.md) | `fun getIntrinsicWidth(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getOpacity](get-opacity.md) | `fun getOpacity(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [setAlpha](set-alpha.md) | `fun setAlpha(alpha: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setColorFilter](set-color-filter.md) | `fun setColorFilter(colorFilter: ColorFilter?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
