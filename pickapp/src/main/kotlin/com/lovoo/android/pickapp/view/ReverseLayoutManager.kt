package com.lovoo.android.pickapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * Custom LinearLayoutManager that triggers scroll to position 0 on view added
 * when it is enabled.
 */
open class ReverseLayoutManager : LinearLayoutManager {

    /**
     * Set to false if you want to disable the auto scroll.
     */
    var scrollEnabled = true

    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun addView(child: View?) {
        super.addView(child)
        if (scrollEnabled) {
            scrollToPosition(0)
        }
    }
}