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
package com.lovoo.android.pickapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickapp.R
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.ImageEngine
import kotlin.properties.Delegates

/**
 * [RecyclerView.Adapter] for the selection bar.
 */
class SelectionAdapter(
    private val slots: Int,
) : RecyclerView.Adapter<SelectionAdapter.ViewHolder>() {
    private val imageLoader: ImageEngine
        get() = PickPicProvider.imageEngine

    private val list = mutableListOf<Uri>()

    /**
     * The click listener for views. Changes take effect when views are bind again.
     */
    var onClickListener: ((View, Uri) -> Unit)? = null

    /**
     * Set the selected item to notify the adapter to update the ui state.
     */
    var selectedUri: Uri? by Delegates.observable(null) { _, old, new ->
        val oldPos = list.indexOf(old)
        val newPos = list.indexOf(new)
        if (oldPos >= 0) {
            notifyItemChanged(oldPos)
        }
        if (newPos >= 0) {
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.create(parent, imageLoader)

    override fun getItemCount() = slots

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uri = list.getOrNull(position)
        holder.bind(uri, uri != null && uri == selectedUri, onClickListener)
    }

    /**
     * @param index the requested item index
     * @return the [Uri] from index or null
     */
    fun get(index: Int) = list.getOrNull(index)

    fun getListCount() = list.size

    /**
     * @param uri the [Uri] that should be added to the selection at list end
     * @return position of the new item
     */
    fun add(uri: Uri): Int {
        val position = list.size
        list.add(uri)
        notifyItemChanged(position)
        return position
    }

    /**
     * @param uri the [Uri] that should be removed from selection
     * @return position of removed item or -1
     */
    fun remove(uri: Uri): Int {
        val position = list.indexOf(uri)
        val count = list.size - position
        if (position >= 0) {
            list.removeAt(position)
            notifyItemRangeChanged(position, count)
            return position
        }
        return -1
    }

    /**
     * Clear the selection list.
     */
    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(
        view: View,
        private val imageEngine: ImageEngine,
    ) : RecyclerView.ViewHolder(view) {
        private val size = view.context.resources.getDimensionPixelSize(R.dimen.pickpic_thumbnail_size)
        private val corner = view.context.resources.getDimensionPixelSize(R.dimen.pickpic_thumbnail_corner_size)

        fun bind(uri: Uri?, isSelected: Boolean, onClickListener: ((View, Uri) -> Unit)?) {
            itemView.isSelected = isSelected
            itemView.setOnClickListener { view ->
                uri?.let { onClickListener?.invoke(view, it) }
            }
            itemView.findViewById<ImageView>(R.id.pickpic_thumbnail_image)?.apply {
                setImageBitmap(null)
                if (uri != null) imageEngine.loadThumbnail(this.context, size, uri, this, corner)
            }
        }

        companion object {
            fun create(parent: ViewGroup, imageEngine: ImageEngine): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.pickpic_layout_thumbnail, parent, false)
                return ViewHolder(view, imageEngine)
            }
        }
    }
}
