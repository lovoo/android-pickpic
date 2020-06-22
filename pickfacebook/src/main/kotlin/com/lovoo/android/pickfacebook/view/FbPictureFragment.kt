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
package com.lovoo.android.pickfacebook.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.facebook.GraphRequest
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.SelectionHolder
import com.lovoo.android.pickcore.contract.ToggleCallback
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickfacebook.R
import com.lovoo.android.pickfacebook.adapter.FbPictureAdapter
import com.lovoo.android.pickfacebook.contract.FbPicturePresenter
import com.lovoo.android.pickfacebook.contract.FbPictureView
import com.lovoo.android.pickfacebook.model.FbPicture
import com.lovoo.android.pickfacebook.presenter.FbPicturePresenterImpl
import com.lovoo.android.pickui.view.PictureDecoration
import kotlinx.android.synthetic.main.pickfacebook_fragment_picture.*

/**
 * Fragment that offers a predefined solution to load and present Facebook pictures from a certain [Gallery].
 *
 * @see FbGalleryFragment
 *
 * Modify pickapp proguard rules as well when package is changed or class is renamed!!!
 */
class FbPictureFragment : Fragment(), FbPictureView {

    private val presenter: FbPicturePresenter = FbPicturePresenterImpl(this)
    private val tag = Object()
    private var gallery: Gallery? = null

    private val selectionHolder: SelectionHolder?
        get() = PickPicProvider.selectionHolder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pickfacebook_fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_view.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = arguments?.getBoolean(ARGUMENT_NESTED_SCROLL) ?: true
            addItemDecoration(PictureDecoration(3, resources.getDimensionPixelOffset(R.dimen.pickpic_picture_item_space)))
            layoutManager = GridLayoutManager(context, 3)
            adapter = FbPictureAdapter(
                context,
                { selectionHolder?.isSelected(it.getUri()) == true },
                { _, picture -> onPictureClick(picture) }
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        selectionHolder?.addToggleListener(
            tag,
            object : ToggleCallback {
                override fun toggle(uri: Uri, gallery: Gallery) {
                    (list_view?.adapter as? FbPictureAdapter)?.let { adapter ->
                        val position = adapter.indexOf { it.getUri() == uri }
                        if (position >= 0) {
                            adapter.notifyItemChanged(position)
                        } else {
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!presenter.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDetach() {
        selectionHolder?.removeToggleListener(tag)
        super.onDetach()
    }

    override fun onDestroy() {
        gallery = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onError(error: Throwable) {
        context?.let {
            val message = error.message ?: it.getString(R.string.pickfacebook_default_error_message)
            Toast.makeText(it, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onPicturesLoaded(pictures: List<FbPicture>, galleryId: String, next: GraphRequest?) {
        val gallery = gallery ?: return
        if (gallery.id != galleryId) return

        (list_view?.adapter as? FbPictureAdapter)?.add(pictures)
        loading_view?.visibility = View.GONE
        next?.let { presenter.next(gallery, it) }
    }

    override fun getLifeCycle() = this

    /**
     * Trigger that the Fragment will switch the current [Gallery] with the given one.
     *
     * @param gallery the new [Gallery] that should be initialised.
     */
    fun swap(gallery: Gallery) {
        this.gallery = gallery
        (list_view?.adapter as? FbPictureAdapter)?.clear()
        loading_view?.visibility = View.VISIBLE
        presenter.swap(gallery)
    }

    private fun onPictureClick(picture: FbPicture) {
        gallery?.let { selectionHolder?.toggle(picture.getUri(), it) }
    }

    companion object {
        const val TAG = "FbPictureFragment"
        private const val ARGUMENT_NESTED_SCROLL = "argumentNestedScroll"

        /**
         * @param allowNestedScroll pass false to prevent nested scrolling
         * @return new instance of [FbPictureFragment]
         */
        fun newInstance(allowNestedScroll: Boolean = true) = FbPictureFragment().apply {
            arguments = Bundle().apply { putBoolean(ARGUMENT_NESTED_SCROLL, allowNestedScroll) }
        }
    }
}
