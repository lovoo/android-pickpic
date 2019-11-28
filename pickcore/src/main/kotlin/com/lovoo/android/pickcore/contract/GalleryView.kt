package com.lovoo.android.pickcore.contract

import androidx.lifecycle.LifecycleOwner
import android.database.Cursor

/**
 * Contract to implement for UI.
 *
 * @see com.lovoo.android.pickui.view.GalleryFragment
 */
interface GalleryView {

    /**
     * @return [LifecycleOwner] of the view for subscription handle
     */
    fun getLifeCycle(): LifecycleOwner

    /**
     * Called when loading process is finished.
     *
     * @param cursor the current loaded [Cursor] or null
     */
    fun onCursorLoaded(cursor: Cursor?)
}