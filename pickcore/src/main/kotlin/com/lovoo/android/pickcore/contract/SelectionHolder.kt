package com.lovoo.android.pickcore.contract

import android.net.Uri
import com.lovoo.android.pickcore.model.Gallery

/**
 * Contract for the selection implementation.
 *
 * @see com.lovoo.android.pickapp.view.PickPicActivity
 */
interface SelectionHolder {

    /**
     * Select or unselect a certain [Uri].
     *
     * @param uri the Uri of the Image
     * @param gallery the current active [Gallery]
     */
    fun toggle(uri: Uri, gallery: Gallery)

    /**
     * @return true if this [Uri] is selected right now
     */
    fun isSelected(uri: Uri): Boolean

    /**
     * Add a listener to subscribe for selection changes.
     *
     * @param tag a unique Any-Object for the listener, see removeToggleListener(tag: Any)
     * @param callback the callback that should be triggered
     */
    fun addToggleListener(tag: Any, callback: ToggleCallback)

    /**
     * Remove a registered listener.
     *
     * @param tag the used object to add the listener, see addToggleListener(tag: Any, callback: ToggleCallback)
     */
    fun removeToggleListener(tag: Any)
}

interface ToggleCallback {
    /**
     * Notify that the selection state for Uri has been changed.
     *
     * @param uri the [Uri] that has been selected or unselected
     * @param gallery the current selected [Gallery]
     */
    fun toggle(uri: Uri, gallery: Gallery)
}