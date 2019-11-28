[pickapp](../../index.md) / [com.lovoo.android.pickapp.model](../index.md) / [Picker](index.md) / [toggle](./toggle.md)

# toggle

`fun toggle(uri: Uri, gallery: Gallery): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/model/Picker.kt#L45)

Add or remove a picture to the map.

### Parameters

`uri` - the picture [Uri](#)

`gallery` - the [Gallery](#) of the picture

**Return**
true if item could be added or removed, false if [PickConfig](-pick-config/index.md) limit is reached

`fun toggle(uris: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<Uri>, galleries: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<Gallery>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickapp/src/main/kotlin/com/lovoo/android/pickapp/model/Picker.kt#L67)

Add or remove multiple items. This calls toggle(picture: Picture, gallery: Gallery) for each entry.
Please make sure that both Arrays are equally sized.

### Parameters

`uris` - array of Uris

`galleries` - array of [Gallery](#)s for each [Picture](#)