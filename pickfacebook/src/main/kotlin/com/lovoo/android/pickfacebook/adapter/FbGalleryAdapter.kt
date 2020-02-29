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
package com.lovoo.android.pickfacebook.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.ImageEngine
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickfacebook.R

/**
 * RecyclerView Adapter to present [Gallery]s.
 *
 * @param context app context
 * @param onClick the callback when an entry was clicked
 */
class FbGalleryAdapter(
  context: Context,
  private val onClick: (View, Gallery) -> Unit
) : RecyclerView.Adapter<FbGalleryAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    private val imageLoader: ImageEngine
        get() = PickPicProvider.imageEngine

    /**
     * Non mutable list of [Gallery]s.
     * Every set will notify adapter.
     */
    var galleries: List<Gallery> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.pickpic_list_item_gallery, parent, false)
        return ViewHolder(view, imageLoader, onClick)
    }

    override fun getItemCount() = galleries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        galleries.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    /**
     * [RecyclerView.ViewHolder] implementation that enforce its own width as height.
     *
     * @param view the current view for this [RecyclerView.ViewHolder]
     * @param engine the [ImageEngine] to load pictures
     * @param onClick click callback for this item
     */
    class ViewHolder(
      view: View,
      private val engine: ImageEngine,
      private val onClick: (View, Gallery) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(gallery: Gallery) {
            itemView.findViewById<TextView>(R.id.gallery_name).text = gallery.name
            itemView.findViewById<TextView>(R.id.gallery_count).text = gallery.count.toString()
            itemView.setOnClickListener { onClick.invoke(it, gallery) }

            itemView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    itemView.viewTreeObserver.removeOnPreDrawListener(this)
                    engine.loadThumbnail(
                            itemView.context,
                            itemView.measuredWidth,
                            Uri.parse(gallery.coverPath),
                            itemView.findViewById(R.id.gallery_cover),
                            0)
                    return true
                }
            })
        }
    }
}
