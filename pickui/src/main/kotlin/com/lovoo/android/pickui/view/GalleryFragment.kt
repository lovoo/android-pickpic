package com.lovoo.android.pickui.view

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ListPopupWindow
import com.lovoo.android.pickcore.Constants
import com.lovoo.android.pickcore.contract.GalleryPresenter
import com.lovoo.android.pickcore.contract.GalleryView
import com.lovoo.android.pickcore.loader.CameraLoader
import com.lovoo.android.pickcore.loader.GalleryLoader
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickcore.model.convertToUi
import com.lovoo.android.pickcore.permission.Permission
import com.lovoo.android.pickcore.presenter.GalleryPresenterImpl
import com.lovoo.android.pickui.R
import com.lovoo.android.pickui.adapter.GalleryAdapter
import com.lovoo.android.pickui.adapter.PopUpGalleryAdapter
import kotlinx.android.synthetic.main.pickpic_fragment_gallery.*
import kotlinx.android.synthetic.main.pickpic_gallery_permission_denied.*

/**
 * Fragment that offers a predefined solution to load and present [Gallery]s and there
 * containing pictures. It also request the Android Storage Permission.
 *
 * @see GalleryFragment.newInstance
 */
class GalleryFragment : Fragment(), GalleryView {

    private val presenter: GalleryPresenter = GalleryPresenterImpl(this)

    private lateinit var allFolderName: String
    private lateinit var adapter: GalleryAdapter
    private var restoredGallery: Gallery? = null
    private val invalidateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val activity = activity ?: return
            if (adapter.itemCount <= 1) {
                presenter.onAttach(activity)
                presenter.load()
            } else {
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            presenter.onAttach(it)
        }
        val filter = IntentFilter().apply {
            addAction(CameraLoader.INTENT_INVALIDATE_GALLERY)
        }
        context.registerReceiver(invalidateReceiver, filter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pickpic_fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = view.context
        allFolderName = context.getString(R.string.pickpic_label_all_folder)

        adapter = GalleryAdapter(context, allFolderName) { _, _ -> }

        restoredGallery = savedInstanceState?.getParcelable("currentGallery")

        if (savedInstanceState == null) {
            activity?.let { requestPermissions(it) }
        }
    }

    override fun onResume() {
        super.onResume()
        checkPermissions()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("currentGallery", restoredGallery)
    }

    override fun onDetach() {
        context?.unregisterReceiver(invalidateReceiver)
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun getLifeCycle() = this

    override fun onCursorLoaded(cursor: Cursor?) {
        adapter.changeCursor(cursor)
        initView()
    }

    private fun onGallerySwitch(gallery: Gallery) {
        restoredGallery = gallery
        folder_text.text = getFolderName(gallery)
        initMenu()
        (childFragmentManager.findFragmentById(R.id.picture_fragment) as? PictureFragment)?.swap(gallery)
    }

    private fun requestPermissions(activity: Activity) {
        val deniedList = Permission.getDeniedPermissions(activity, Permission.galleryPermissions)

        val permissions = Array(deniedList.size) { i -> deniedList[i].first }
        Permission.requestPermissions(this, PERMISSION_REQUEST_CODE, permissions)
    }

    private fun checkPermissions() {
        val activity = activity ?: return
        val deniedList = Permission.getDeniedPermissions(activity, Permission.galleryPermissions)

        permission_layout.visibility = when (deniedList.isNotEmpty()) {
            true -> View.VISIBLE

            false -> {
                if (permission_layout.visibility == View.VISIBLE) {
                    presenter.load()
                }
                View.GONE
            }
        }

        permission_button?.setOnClickListener { _ ->
            deniedList.firstOrNull { it.second }?.let {
                Permission.openSettings(activity)
            } ?: requestPermissions(activity)
        }
    }

    private fun getFolderName(gallery: Gallery) = when (gallery.name) {
        Constants.All_FOLDER_NAME -> allFolderName
        else -> gallery.name
    }

    private fun initView() {
        restoredGallery?.let {
            onGallerySwitch(it)
        } ?: run {
            adapter.getItem(0) { GalleryLoader.convert(it).convertToUi() }?.let {
                onGallerySwitch(it)
            }
        }
    }

    private fun initMenu() {
        val items = adapter.getItems { GalleryLoader.convert(it).convertToUi() }

        if (items.size <= 1) {
            folder_button.visibility = View.GONE
            return
        }

        folder_button?.let { view ->
            val popup = ListPopupWindow(view.context).apply {
                val adapterItems = items.filter { it != restoredGallery }
                setAdapter(PopUpGalleryAdapter(view.context, adapterItems) { getFolderName(it) })
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
        private const val PERMISSION_REQUEST_CODE = 30

        /**
         * @return new instance of [GalleryFragment]
         */
        fun newInstance() = GalleryFragment()
    }
}
