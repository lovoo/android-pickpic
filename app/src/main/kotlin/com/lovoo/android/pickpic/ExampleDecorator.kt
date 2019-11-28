package com.lovoo.android.pickpic

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ExampleDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val pos = parent.layoutManager?.getPosition(view) ?: -1
        val count = parent.layoutManager?.itemCount ?: 0
        val res = when (pos == count - 1) {
            true -> R.dimen.example_last_item_padding_bottom
            else -> R.dimen.example_item_padding_bottom
        }
        outRect.bottom = view.resources.getDimensionPixelOffset(res)
    }
}