package com.lovoo.android.pickcore.presenter

import androidx.lifecycle.Observer
import androidx.fragment.app.FragmentActivity
import androidx.loader.app.LoaderManager
import com.lovoo.android.pickcore.contract.PicturePresenter
import com.lovoo.android.pickcore.contract.PictureView
import com.lovoo.android.pickcore.loader.Collector
import com.lovoo.android.pickcore.loader.PictureLoader
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickcore.model.convertToLib

/**
 * Implementation of [PicturePresenter] for Fragments to load the pictures from a [Gallery].
 * Don't forget to forward onDestroy().
 *
 * Start loading with swap().
 *
 * @param view the contract to the UI Layer
 */
class PicturePresenterImpl(val view: PictureView) : PicturePresenter {

    private var collector: Collector? = null

    /**
     * Clear the last [Collector] and and creates a new [Collector] to load the device pictures from gallery.
     * Result will be forwarded in [PictureView].onCursorLoaded([Cursor]?).
     *
     * @param activity the current [FragmentActivity]
     * @param gallery the current selected [Gallery]
     */
    override fun swap(activity: FragmentActivity, gallery: Gallery) {
        collector?.onDestroy()
        collector = Collector(LoaderManager.getInstance(activity), PictureLoader.newInstance(activity, gallery.convertToLib()), 2).apply {
            cursor.observe(view.getLifeCycle(), Observer { t -> view.onCursorLoaded(t) })
            load()
        }
    }

    /**
     * Clear the current [Collector].
     */
    override fun onDestroy() {
        collector?.onDestroy()
    }
}