package com.lovoo.android.pickcore.presenter

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.loader.app.LoaderManager
import com.lovoo.android.pickcore.contract.GalleryPresenter
import com.lovoo.android.pickcore.contract.GalleryView
import com.lovoo.android.pickcore.loader.Collector
import com.lovoo.android.pickcore.loader.GalleryLoader

/**
 * Implementation of [GalleryPresenter] for Fragments to load the device galleries.
 * Don't forget to forward these events:
 *  onAttach()
 *  onCreate()
 *  onDestroy()
 *
 * Start loading with load() if you have the permission to access the Filesystem.
 *
 * @param view the contract to the UI Layer
 */
class GalleryPresenterImpl(private val view: GalleryView) : GalleryPresenter {


    private var collector: Collector? = null

    /**
     * Clear the last [Collector] and create a new [Collector] to load the device galleries.
     * Result will be forwarded in [GalleryView].onCursorLoaded([Cursor]?).
     *
     * @param activity the current [FragmentActivity]
     */
    override fun onAttach(activity: FragmentActivity) {
        collector?.onDestroy()
        collector = Collector(LoaderManager.getInstance(activity), GalleryLoader.newInstance(activity), 1).apply {
            cursor.observe(view.getLifeCycle(), Observer { t -> view.onCursorLoaded(t) })
        }
    }

    /**
     * Currently empty implemented.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // no-op
    }

    /**
     * Clear the current [Collector].
     */
    override fun onDestroy() {
        collector?.onDestroy()
    }

    /**
     * Start the loading process if onAttach was called already.
     */
    override fun load() {
        collector?.load()
    }
}