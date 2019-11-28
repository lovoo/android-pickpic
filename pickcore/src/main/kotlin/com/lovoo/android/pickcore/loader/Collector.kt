package com.lovoo.android.pickcore.loader

import androidx.lifecycle.MutableLiveData
import android.database.Cursor
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader

/**
 * An [LoaderManager.LoaderCallbacks] implementation that load a cursor
 * and set the [MutableLiveData] cursor. Others can observe this.
 *
 * Do not forget to call onDestroy()!!!
 *
 * @param manager the [LoaderManager] instance from Activity or Fragment
 * @param loader the [Loader] instance that fills the cursor
 * @param id a unique identifier to register the loader within the manager
 */
class Collector(
        private val manager: LoaderManager,
        private val loader: Loader<Cursor>,
        private val id: Int
) : LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Observable field that emit null or an [Cursor] with data from the [Loader].
     */
    val cursor = MutableLiveData<Cursor?>()

    override fun onCreateLoader(id: Int, args: Bundle?) = loader

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        cursor.postValue(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        cursor.postValue(null)
    }

    /**
     * Destroys the registered [Loader] within the [LoaderManager].
     */
    fun onDestroy() {
        manager.destroyLoader(id)
    }

    /**
     * Start to load data from the [Loader], when finished a new [Cursor] is posted.
     */
    fun load() {
        manager.initLoader(id, null, this)
    }
}