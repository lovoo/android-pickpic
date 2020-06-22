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
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickcore.Constants
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.adapter.RecyclerViewCursorAdapter
import com.lovoo.android.pickcore.loader.GalleryLoader
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickcore.model.convertToUi
import com.lovoo.android.pickui.R
import kotlinx.android.synthetic.main.pickpic_list_item_gallery.view.*
import java.io.File

/**
 * RecyclerView Adapter to present [Gallery]s.
 *
 * @param context app context
 * @param allFolderName the display name for the all Folder entry
 * @param onClick the callback when an entry was clicked
 */
class GalleryAdapter(
    context: Context,
    private val allFolderName: String,
    private val onClick: (View, Gallery) -> Unit
) : RecyclerViewCursorAdapter<RecyclerView.ViewHolder>(null) {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.pickpic_list_item_gallery, parent, false)
        return ViewHolder(view, allFolderName, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, cursor: Cursor) {
        (holder as ViewHolder).bind(GalleryLoader.convert(cursor).convertToUi())
    }

    private class ViewHolder(
        view: View,
        private val allFolderName: String,
        private val onClick: (View, Gallery) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(gallery: Gallery) {
            itemView.gallery_name.text = gallery.name
            itemView.gallery_count.text = gallery.count.toString()
            itemView.setOnClickListener { onClick.invoke(itemView, gallery) }

            itemView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    itemView.viewTreeObserver.removeOnPreDrawListener(this)
                    PickPicProvider.imageEngine.loadThumbnail(
                        itemView.context,
                        itemView.measuredWidth,
                        Uri.fromFile(File(gallery.coverPath)),
                        itemView.gallery_cover,
                        0
                    )
                    return true
                }
            })

            if (gallery.name == Constants.All_FOLDER_NAME) {
                itemView.gallery_name?.text = allFolderName
            }
        }
    }
}
