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
package com.lovoo.android.pickui.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.adapter.RecyclerViewCursorAdapter
import com.lovoo.android.pickcore.loader.PictureLoader
import com.lovoo.android.pickcore.model.Picture
import com.lovoo.android.pickcore.model.convertToUi
import com.lovoo.android.pickui.R

/**
 * RecyclerView Adapter to present [Picture]s.
 *
 * It supports two view types:
 *  - camera button
 *  - selectable picture image
 *
 * @param context app context
 * @param selectionLookUp determine if the [Picture] is selected
 * @param onClick the callback when an entry was clicked
 */
class PictureAdapter(
    context: Context,
    private val selectionLookUp: (Picture) -> Boolean,
    private val onClick: (View, Picture) -> Unit,
) : RecyclerViewCursorAdapter<RecyclerView.ViewHolder>(null) {

    private val inflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return when (getItemId(position)) {
            PictureLoader.CAMERA_ID -> TYPE_CAMERA
            else -> TYPE_PICTURE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = when (viewType) {
            TYPE_CAMERA -> R.layout.pickpic_list_item_camera
            else -> R.layout.pickpic_list_item_picture
        }
        val view = inflater.inflate(layout, parent, false)
        return ViewHolder(view, selectionLookUp, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, cursor: Cursor) {
        val picture = PictureLoader.convert(cursor).convertToUi()

        if (picture.id == PictureLoader.CAMERA_ID) {
            (holder as ViewHolder).bindCameraItem(PickPicProvider.cameraEngine)
        } else {
            (holder as ViewHolder).bind(picture)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as ViewHolder).recycle()
    }

    private class ViewHolder(
        view: View,
        selectionLookUp: (Picture) -> Boolean,
        onClick: (View, Picture) -> Unit,
    ) : com.lovoo.android.pickui.adapter.ViewHolder<Picture>(

        view,
        PickPicProvider.imageEngine,
        selectionLookUp,
        onClick,
    ) {

        override fun getUri(item: Picture) = item.getUri()
    }

    companion object {
        private const val TYPE_CAMERA = -1
        private const val TYPE_PICTURE = 0
    }
}
