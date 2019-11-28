package com.lovoo.android.pickcore.contract

import androidx.fragment.app.FragmentActivity
import com.lovoo.android.pickcore.model.Gallery

/**
 * Contract to implement for Presenter.
 *
 * @see com.lovoo.android.pickcore.presenter.PicturePresenterImpl
 */
interface PicturePresenter {

    /**
     * Trigger cursor loading for given [Gallery] and destroy the last loaded cursor.
     *
     * @param activity the current [FragmentActivity]
     * @param gallery the [Gallery] that should be used now
     */
    fun swap(activity: FragmentActivity, gallery: Gallery)

    /**
     * Notify that the Fragment got destroyed.
     */
    fun onDestroy()
}