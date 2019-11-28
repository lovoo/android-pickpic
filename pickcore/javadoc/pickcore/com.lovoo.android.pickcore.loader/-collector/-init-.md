[pickcore](../../index.md) / [com.lovoo.android.pickcore.loader](../index.md) / [Collector](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Collector(manager: LoaderManager, loader: Loader<Cursor>, id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`

An [LoaderManager.LoaderCallbacks](#) implementation that load a cursor
and set the [MutableLiveData](#) cursor. Others can observe this.

Do not forget to call onDestroy()!!!

### Parameters

`manager` - the [LoaderManager](#) instance from Activity or Fragment

`loader` - the [Loader](#) instance that fills the cursor

`id` - a unique identifier to register the loader within the manager