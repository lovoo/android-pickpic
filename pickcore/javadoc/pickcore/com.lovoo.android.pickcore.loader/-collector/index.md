[pickcore](../../index.md) / [com.lovoo.android.pickcore.loader](../index.md) / [Collector](./index.md)

# Collector

`class Collector : LoaderCallbacks<Cursor>` [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/loader/Collector.kt#L19)

An [LoaderManager.LoaderCallbacks](#) implementation that load a cursor
and set the [MutableLiveData](#) cursor. Others can observe this.

Do not forget to call onDestroy()!!!

### Parameters

`manager` - the [LoaderManager](#) instance from Activity or Fragment

`loader` - the [Loader](#) instance that fills the cursor

`id` - a unique identifier to register the loader within the manager

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Collector(manager: LoaderManager, loader: Loader<Cursor>, id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>An [LoaderManager.LoaderCallbacks](#) implementation that load a cursor and set the [MutableLiveData](#) cursor. Others can observe this. |

### Properties

| Name | Summary |
|---|---|
| [cursor](cursor.md) | `val cursor: MutableLiveData<Cursor?>`<br>Observable field that emit null or an [Cursor](#) with data from the [Loader](#). |

### Functions

| Name | Summary |
|---|---|
| [load](load.md) | `fun load(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Start to load data from the [Loader](#), when finished a new [Cursor](#) is posted. |
| [onCreateLoader](on-create-loader.md) | `fun onCreateLoader(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, args: Bundle?): Loader<Cursor>` |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Destroys the registered [Loader](#) within the [LoaderManager](#). |
| [onLoaderReset](on-loader-reset.md) | `fun onLoaderReset(loader: Loader<Cursor>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onLoadFinished](on-load-finished.md) | `fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
