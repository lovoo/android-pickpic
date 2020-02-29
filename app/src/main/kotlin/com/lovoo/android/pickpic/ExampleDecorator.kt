/**
 * Copyright 2018 LOVOO GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
