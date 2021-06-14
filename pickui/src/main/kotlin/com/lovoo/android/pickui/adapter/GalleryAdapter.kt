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
import com.lovoo.android.pickui.databinding.PickpicListItemGalleryBinding
import java.io.File

/**
 * RecyclerView Adapter to present [Gallery]s.
 *
 * @param context app context
 * @param allFolderName the display name for the all Folder entry
 * @param onClick the callback when an entry was clicked
 */
class GalleryAdapter(
    private val allFolderName: String,
    private val onClick: (View, Gallery) -> Unit
) : RecyclerViewCursorAdapter<RecyclerView.ViewHolder>(null) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PickpicListItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, allFolderName, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, cursor: Cursor) {
        (holder as ViewHolder).bind(GalleryLoader.convert(cursor).convertToUi())
    }

    class ViewHolder(
        private val binding: PickpicListItemGalleryBinding,
        private val allFolderName: String,
        private val onClick: (View, Gallery) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(gallery: Gallery) {
            binding.apply {
                galleryName.text = gallery.name
                galleryCount.text = gallery.count.toString()
                itemView.setOnClickListener { onClick.invoke(itemView, gallery) }
                itemView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        itemView.viewTreeObserver.removeOnPreDrawListener(this)
                        PickPicProvider.imageEngine.loadThumbnail(
                            itemView.context,
                            itemView.measuredWidth,
                            Uri.fromFile(File(gallery.coverPath)),
                            galleryCover,
                            0
                        )
                        return true
                    }
                })

                if (gallery.name == Constants.All_FOLDER_NAME) {
                    galleryName.text = allFolderName
                }
            }
        }
    }
}
