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
import com.lovoo.android.pickfacebook.databinding.PickfacebookFragmentGalleryBinding
import com.lovoo.android.pickfacebook.presenter.FbGalleryPresenterImpl
import com.lovoo.android.pickui.adapter.PopUpGalleryAdapter

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
    private var _binding: PickfacebookFragmentGalleryBinding? = null
    private val binding get() = _binding!!

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
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = PickfacebookFragmentGalleryBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            permissionInclude.permissionLayout.visibility = View.GONE
            errorInclude.errorLayout.visibility = View.GONE
        }

        if (childFragmentManager.findFragmentByTag(FbPictureFragment.TAG) == null) {
            val allowNestedScroll = (arguments?.getBoolean(ARGUMENT_NESTED_SCROLL) ?: true)
            val fragment = FbPictureFragment.newInstance(allowNestedScroll)
            childFragmentManager.beginTransaction().add(R.id.picture_fragment, fragment, FbPictureFragment.TAG).commitNow()
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
        binding.errorInclude.errorLayout.visibility = View.GONE
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
        binding.errorInclude.apply {
            errorMessage.text = message
            errorButton.setOnClickListener {
                presenter.handleError(error)
            }
            errorLayout.visibility = View.VISIBLE
        }

        adapter?.galleries = emptyList()
        adapter = null
    }

    private fun onGallerySwitch(gallery: Gallery) {
        restoredGallery = gallery
        binding.folderText.text = gallery.name
        initMenu()
        (childFragmentManager.findFragmentByTag(FbPictureFragment.TAG) as? FbPictureFragment)?.swap(gallery)
    }

    private fun checkPermissions() {
        val isValid = presenter.isLoggedIn() && presenter.isPicturePermissionGranted()
        binding.permissionInclude.apply {
            permissionLayout.visibility = when (!isValid) {
                true -> View.VISIBLE
                false -> View.GONE
            }
            permissionButton.setOnClickListener {
                presenter.loginWithPicturePermission(this@FbGalleryFragment)
            }
        }
        if (isValid && adapter == null) {
            presenter.loadGalleries()
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

        binding.folderButton.let { view ->
            if (items.size <= 1) {
                view.visibility = View.GONE
                return
            }
            val popup = ListPopupWindow(view.context).apply {
                val adapterItems = items.filter { it != restoredGallery }
                setAdapter(PopUpGalleryAdapter(view.context, adapterItems) { it.name })
                anchorView = view
                setContentWidth(resources.getDimensionPixelSize(R.dimen.pickpic_gallery_item_width))
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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
