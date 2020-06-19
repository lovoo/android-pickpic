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
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.BaseAdapter
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickui.R
import kotlinx.android.synthetic.main.pickpic_list_item_gallery.view.*
import java.io.File

/**
 * Adapter for ListView or PopUpWindows.
 * Create preview layout for [Gallery]s with a cover image,
 * display name and the amount of containing pictures.
 *
 * @param context the app context
 * @param items the list of [Gallery]s
 * @param folderNameLookUp determine the display name for a [Gallery]
 */
class PopUpGalleryAdapter(
    private val context: Context,
    private val items: List<Gallery>,
    private val folderNameLookUp: (Gallery) -> String
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        val item = getItem(position) ?: return View(context)
        val view = convertView
            ?: inflater.inflate(R.layout.pickpic_list_item_gallery, container, false)

        view.gallery_cover.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                view.gallery_cover.viewTreeObserver.removeOnPreDrawListener(this)
                val file = File(item.coverPath)
                val uri = when (file.exists()) {
                    true -> Uri.fromFile(file)
                    false -> Uri.parse(item.coverPath)
                }
                PickPicProvider.imageEngine.loadThumbnail(
                    context,
                    view.gallery_cover.measuredHeight,
                    uri,
                    view.gallery_cover,
                    0
                )
                return true
            }
        })
        view.gallery_name.text = folderNameLookUp.invoke(item)
        view.gallery_count.text = item.count.toString()

        return view
    }

    override fun getItem(position: Int) = items.getOrNull(position)

    override fun getItemId(position: Int) = items.getOrNull(position)?.id?.hashCode()?.toLong()
        ?: 0L

    override fun getCount() = items.size
}
