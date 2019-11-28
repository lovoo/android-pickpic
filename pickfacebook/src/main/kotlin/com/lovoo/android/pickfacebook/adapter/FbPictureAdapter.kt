package com.lovoo.android.pickfacebook.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.ImageEngine
import com.lovoo.android.pickfacebook.R
import com.lovoo.android.pickfacebook.model.FbPicture

/**
 * RecyclerView Adapter to present [FbPicture]s.
 *
 * @param context app context
 * @param selectionLookUp determine if the [FbPicture] is selected
 * @param onClick the callback when an entry was clicked
 */
class FbPictureAdapter(
        context: Context,
        private val selectionLookUp: (FbPicture) -> Boolean,
        private val onClick: (View, FbPicture) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    private val lock = Object()

    private val imageLoader: ImageEngine
        get() = PickPicProvider.imageEngine

    /**
     * Mutable list of [FbPicture]s
     */
    private val pictures = mutableListOf<FbPicture>()

    override fun getItemCount() = pictures.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.pickpic_list_item_picture, parent, false)
        return ViewHolder(view, imageLoader, selectionLookUp, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        synchronized(lock) {
            pictures.getOrNull(position)?.let {
                (holder as ViewHolder).bind(it)
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as ViewHolder).recycle()
    }

    /**
     * @param filter Filter that should return true if given [FbPicture] was searched
     * @return index of first [FbPicture] that returns true from passed filter or -1 if not found
     */
    fun indexOf(filter: (FbPicture) -> Boolean): Int {
        synchronized(lock) {
            return pictures.indexOfFirst(filter)
        }
    }

    /**
     * Add additional items to the list.
     *
     * @param list the [List] of [FbPicture]s that should be added to the end
     */
    fun add(list: List<FbPicture>) {
        synchronized(lock) {
            val start = pictures.size
            pictures.addAll(list)
            notifyItemRangeInserted(start, list.size)
        }
    }

    /**
     * Clear the whole list of [FbPicture]s
     */
    fun clear() {
        synchronized(lock) {
            pictures.clear()
            notifyDataSetChanged()
        }
    }

    private class ViewHolder(
            view: View,
            engine: ImageEngine,
            selectionLookUp: (FbPicture) -> Boolean,
            onClick: (View, FbPicture) -> Unit
    ) : com.lovoo.android.pickui.adapter.ViewHolder<FbPicture>(

            view,
            engine,
            selectionLookUp,
            onClick) {

        override fun getUri(item: FbPicture): Uri = item.getUri()
    }
}