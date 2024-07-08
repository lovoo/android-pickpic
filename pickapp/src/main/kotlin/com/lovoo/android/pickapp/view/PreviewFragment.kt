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
package com.lovoo.android.pickapp.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.lovoo.android.pickapp.R
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.ImageEngine

/**
 * The [Fragment] that is used for the [Preview] ViewPager.
 *
 * @see PreviewFragment.createInstance
 */
class PreviewFragment : Fragment() {

    private val imageEngine: ImageEngine
        get() = PickPicProvider.imageEngine

    private var uri: Uri? = null
    private var position: Int = PagerAdapter.POSITION_NONE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pickpic_layout_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            uri = it.getParcelable(ARGUMENT_URI)
            position = it.getInt(ARGUMENT_POS, PagerAdapter.POSITION_NONE)
        }
        loadImage()
    }

    private fun loadImage() {
        val uri = uri ?: return

        view?.post {
            (view as? ImageView)?.let {
                imageEngine.loadImage(
                    it.context,
                    it.measuredWidth,
                    it.measuredHeight,
                    uri,
                    it,
                    0,
                )
            }
        }
    }

    companion object {

        private const val ARGUMENT_URI = "preview_uri"
        private const val ARGUMENT_POS = "preview_pos"

        /**
         * Create a new instance of [PreviewFragment] and set argument.
         *
         * @param position the adapter position
         * @param uri the picture [Uri] or null
         *
         * @return the [PreviewFragment]
         */
        fun createInstance(position: Int, uri: Uri?) = PreviewFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_URI, uri)
                putInt(ARGUMENT_POS, position)
            }
        }
    }
}
