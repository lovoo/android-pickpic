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
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickfacebook.Facebook
import com.lovoo.android.pickfacebook.R
import com.lovoo.android.pickfacebook.adapter.FbGalleryAdapter
import com.lovoo.android.pickfacebook.contract.FbGalleryPresenter
import com.lovoo.android.pickfacebook.contract.FbGalleryView
import com.lovoo.android.pickfacebook.presenter.FbGalleryPresenterImpl
import com.lovoo.android.pickui.adapter.PopUpGalleryAdapter
import kotlinx.android.synthetic.main.pickfacebook_fragment_gallery.*
import kotlinx.android.synthetic.main.pickfacebook_gallery_error.*
import kotlinx.android.synthetic.main.pickfacebook_gallery_permission_denied.*

/**
 * Fragment that offers a predefined solution to load and present Facebook [Gallery] and there
 * containing pictures. It also request the Facebook user-photos Permission.
 *
 * @see FbGalleryFragment.newInstance
 * @see com.lovoo.android.pickui.view.GalleryFragment
 */
class FbGalleryFragment : Fragment(), FbGalleryView {

    private val presenter: FbGalleryPresenter = FbGalleryPresenterImpl(this)

    private var adapter: FbGalleryAdapter? = null
    private var restoredGallery: Gallery? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            presenter.onAttach(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pickfacebook_fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permission_layout.visibility = View.GONE
        error_layout.visibility = View.GONE

        if (childFragmentManager.findFragmentByTag(FbPictureFragment.TAG) == null) {
            val allowNestedScroll = (arguments?.getBoolean(ARGUMENT_NESTED_SCROLL) ?: true)
            val fragment = FbPictureFragment.newInstance(allowNestedScroll)
            childFragmentManager.beginTransaction().add(R.id.picture_fragment, fragment, FbPictureFragment.TAG)
        }

        restoredGallery = savedInstanceState?.getParcelable("currentGallery")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!presenter.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            checkPermissions()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("currentGallery", restoredGallery)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun getLifeCycle() = this

    override fun onAccessTokenChanged(token: String?) {
        error_layout.visibility = View.GONE
        checkPermissions()
    }

    override fun onUserChanged(user: Facebook.User?) {
        // no-op
    }

    override fun onGalleriesLoaded(galleries: List<Gallery>) {
        val context = context ?: return
        adapter = FbGalleryAdapter(context) { _, _ -> }.apply {
            this.galleries = galleries
        }
        initView()
    }

    override fun onError(error: Throwable) {
        val context = context ?: return
        val message = when {
            !error.localizedMessage.isNullOrEmpty() -> error.localizedMessage
            !error.message.isNullOrEmpty() -> error.message
            else -> context.getString(R.string.pickfacebook_default_error_message)
        }
        error_message?.text = message
        error_button?.setOnClickListener {
            presenter.handleError(error)
        }

        error_layout?.visibility = View.VISIBLE

        adapter?.galleries = emptyList()
        adapter = null
    }

    private fun onGallerySwitch(gallery: Gallery) {
        restoredGallery = gallery
        folder_text.text = gallery.name
        initMenu()
        (childFragmentManager.findFragmentByTag(FbPictureFragment.TAG) as? FbPictureFragment)?.swap(gallery)
    }

    private fun checkPermissions() {
        val isValid = presenter.isLoggedIn() && presenter.isPicturePermissionGranted()

        permission_layout?.visibility = when (!isValid) {
            true -> View.VISIBLE
            false -> View.GONE
        }

        if (isValid && adapter == null) {
            presenter.loadGalleries()
        }

        permission_button?.setOnClickListener {
            presenter.loginWithPicturePermission(this)
        }
    }

    private fun initView() {
        restoredGallery?.let {
            onGallerySwitch(it)
        } ?: run {
            adapter?.galleries?.getOrNull(0)?.let {
                onGallerySwitch(it)
            }
        }
    }

    private fun initMenu() {
        val items = adapter?.galleries ?: return

        if (items.size <= 1) {
            folder_button.visibility = View.GONE
            return
        }

        folder_button?.let { view ->
            val popup = ListPopupWindow(view.context).apply {
                val adapterItems = items.filter { it != restoredGallery }
                setAdapter(PopUpGalleryAdapter(view.context, adapterItems) { it.name })
                anchorView = view
                setContentWidth(view.resources.getDimensionPixelSize(R.dimen.pickpic_gallery_item_width))
                setDropDownGravity(Gravity.END)
                setOnItemClickListener { _, _, position, _ ->
                    adapterItems.getOrNull(position)?.let {
                        onGallerySwitch(it)
                        this.dismiss()
                    }
                }
                isModal = true
                view.setOnTouchListener(createDragToOpenListener(view))
            }
            view.setOnClickListener {
                popup.show()
            }
            view.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val ARGUMENT_NESTED_SCROLL = "argumentNestedScroll"

        /**
         * @param allowNestedScroll pass false to prevent nested scrolling
         * @return new instance of [FbGalleryFragment]
         */
        fun newInstance(allowNestedScroll: Boolean = true) = FbGalleryFragment().apply {
            arguments = Bundle().apply { putBoolean(ARGUMENT_NESTED_SCROLL, allowNestedScroll) }
        }
    }
}
