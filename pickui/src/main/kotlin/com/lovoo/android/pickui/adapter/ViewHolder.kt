package com.lovoo.android.pickui.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import com.lovoo.android.pickcore.contract.CameraEngine
import com.lovoo.android.pickcore.contract.ImageEngine
import com.lovoo.android.pickui.R
import com.lovoo.android.pickui.view.PlaceHolderDrawable

/**
 * Typed [RecyclerView.ViewHolder] implementation that enforce its own width as height.
 *
 * @param view the current view for this [RecyclerView.ViewHolder]
 * @param engine the [ImageEngine] to load pictures
 * @param selectionLookUp determine if the item is selected
 * @param onClick click callback for this item
 */
abstract class ViewHolder<T>(
        view: View,
        private val engine: ImageEngine,
        private val selectionLookUp: (T) -> Boolean,
        private val onClick: (View, T) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val size = MutableLiveData<Int>()

    /**
     * Determine the [Uri] that should be used for the [ImageEngine].
     * @param item the requested item
     *
     * @return the [Uri] for this item
     */
    abstract fun getUri(item: T): Uri

    init {
        // wait for the first draw cycle
        view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                view.viewTreeObserver.removeOnPreDrawListener(this)
                // publish the measured width
                size.postValue(view.measuredWidth)
                return true
            }
        })
    }

    /**
     * Reset the view.
     */
    fun recycle() {
        val width = size.value ?: 0
        itemView.findViewById<ImageView>(R.id.picture)?.setImageDrawable(PlaceHolderDrawable().apply {
            this.width = width
            this.height = width
        })
    }

    /**
     * Set select state, click listener and load an image.
     *
     * @param item the requested item for this view
     */
    fun bind(item: T) {
        val width = size.value ?: 0
        if (width == 0) {
            // wait until the view width is set
            size.observeForever(object : Observer<Int> {
                override fun onChanged(width: Int?) {
                    size.removeObserver(this)
                    bind(item)
                }
            })
            return
        }

        val isSelected = selectionLookUp.invoke(item)
        val uri = getUri(item)
        val imageView = itemView.findViewById<ImageView>(R.id.picture)
        itemView.isSelected = isSelected
        itemView.findViewById<AppCompatCheckBox>(R.id.picture_selected).isChecked = isSelected
        itemView.setOnClickListener {
            onClick.invoke(it, item)
        }
        engine.loadThumbnail(imageView.context, width, uri, imageView, 0)
    }

    /**
     * Set display text an click listener for the camera button.
     *
     * @param cameraEngine the engine to start the camera and to get the display text
     */
    fun bindCameraItem(cameraEngine: CameraEngine) {
        val width = size.value ?: 0
        if (width == 0) {
            // wait until the view width is set
            size.observeForever(object : Observer<Int> {
                override fun onChanged(width: Int?) {
                    size.removeObserver(this)
                    bindCameraItem(cameraEngine)
                }
            })
            return
        }

        itemView.isSelected = false
        itemView.findViewById<TextView>(R.id.camera_text).text = cameraEngine.getDisplayName()
        itemView.setOnClickListener { cameraEngine.startCamera() }
        itemView.layoutParams?.let { it.height = width }
        itemView.requestLayout()
    }
}