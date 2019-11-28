[pickcore](../../index.md) / [com.lovoo.android.pickcore.permission](../index.md) / [Permission](index.md) / [getDeniedPermissions](./get-denied-permissions.md)

# getDeniedPermissions

`fun getDeniedPermissions(activity: Activity, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>>` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/permission/Permission.kt#L44)

Validate if all permissions are granted (result empty).

### Parameters

`activity` - current activity

`permissions` - list of requested permissions

**Return**
list of denied permissions and if they are denied always

