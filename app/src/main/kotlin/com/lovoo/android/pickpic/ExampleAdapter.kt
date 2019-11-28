package com.lovoo.android.pickpic

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ExampleAdapter(context: Context) : RecyclerView.Adapter<ExampleAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    var list: List<Uri> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.example_item, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(uri: Uri) {
            (itemView as? ImageView)?.let {
                it.setImageBitmap(null)
                Glide.with(it).load(uri).into(it)
            }
        }
    }
}