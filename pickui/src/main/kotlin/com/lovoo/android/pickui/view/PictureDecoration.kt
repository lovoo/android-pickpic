package com.lovoo.android.pickui.view

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

/**
 * Custom decoration for items of an RecycleView that should get
 * equal space between each other and its parent.
 */
class PictureDecoration(
        private val spanCount: Int,
        private val padding: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        if (position % spanCount > 0) outRect.left = padding
        if (position >= spanCount) outRect.top = padding
        if ((position + 1) % spanCount > 0) outRect.right = padding

        outRect.bottom = padding // bug
    }
}