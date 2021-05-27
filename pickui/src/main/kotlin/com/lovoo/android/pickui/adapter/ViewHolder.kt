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

import android.net.Uri
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.CameraEngine
import com.lovoo.android.pickcore.contract.ImageEngine
import com.lovoo.android.pickml.evaluator.ProfilePictureEvaluator
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
        itemView.findViewById<ImageView>(R.id.picture)?.setImageDrawable(
            PlaceHolderDrawable().apply {
                this.width = width
                this.height = width
            }
        )
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
        val textView = itemView.findViewById<TextView>(R.id.pictureRate)
        itemView.isSelected = isSelected
        itemView.findViewById<AppCompatCheckBox>(R.id.picture_selected).isChecked = isSelected
        itemView.setOnClickListener {
            onClick.invoke(it, item)
        }
        textView.text = " ... "
        imageView.setImageBitmap(null)
        engine.loadBitmap(imageView.context, width, uri, 0) { bitmap ->
            if (bitmap != null) {
                (PickPicProvider.pictureEvaluatorEngine as? ProfilePictureEvaluator)?.let { evaluator ->
                    evaluator.categorizePicture(bitmap) { data ->
                        val value = evaluator.rateCategory(data)
                        textView.text = "$value"
                    }
                }
            }
            imageView.setImageBitmap(bitmap)
        }
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
