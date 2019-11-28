package com.lovoo.android.pickcore.contract

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * Contract to implement for Presenter.
 *
 * @see com.lovoo.android.pickcore.presenter.GalleryPresenterImpl
 */
interface GalleryPresenter {

    /**
     * Notify that the Fragment got attached.
     *
     * @param activity the [FragmentActivity] that holds the Fragment
     */
    fun onAttach(activity: FragmentActivity)

    /**
     * Notify that the Fragment is created or restored.
     *
     * @param savedInstanceState the restored [Bundle]
     */
    fun onCreate(savedInstanceState: Bundle?)

    /**
     * Notify that the Fragment got destroyed.
     */
    fun onDestroy()

    /**
     * Start to load all device galleries.
     */
    fun load()
}