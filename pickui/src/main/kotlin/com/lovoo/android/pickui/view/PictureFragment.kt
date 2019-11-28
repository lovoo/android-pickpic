package com.lovoo.android.pickui.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.PicturePresenter
import com.lovoo.android.pickcore.contract.PictureView
import com.lovoo.android.pickcore.contract.SelectionHolder
import com.lovoo.android.pickcore.contract.ToggleCallback
import com.lovoo.android.pickcore.loader.CameraLoader
import com.lovoo.android.pickcore.loader.PictureLoader
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickcore.model.Picture
import com.lovoo.android.pickcore.model.convertToUi
import com.lovoo.android.pickcore.presenter.PicturePresenterImpl
import com.lovoo.android.pickui.R
import com.lovoo.android.pickui.adapter.PictureAdapter
import kotlinx.android.synthetic.main.pickpic_fragment_picture.*

/**
 * Fragment that offers a predefined solution to load and present [Picture]s from a certain [Gallery].
 *
 * @see GalleryFragment
 */
class PictureFragment : Fragment(), PictureView {

    private val presenter: PicturePresenter = PicturePresenterImpl(this)
    private val tag = Object()
    private var gallery: Gallery? = null

    private val selectionHolder: SelectionHolder?
        get() = PickPicProvider.selectionHolder

    private val invalidateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            gallery?.let {
                swap(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pickpic_fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_view.apply {
            setHasFixedSize(true)
            addItemDecoration(PictureDecoration(3, resources.getDimensionPixelOffset(R.dimen.pickpic_picture_item_space)))
            layoutManager = GridLayoutManager(context, 3)
            adapter = PictureAdapter(context,
                    { selectionHolder?.isSelected(it.getUri()) == true },
                    { _, picture -> onPictureClick(picture) })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        selectionHolder?.addToggleListener(tag, object : ToggleCallback {
            override fun toggle(uri: Uri, gallery: Gallery) {
                (list_view.adapter as? PictureAdapter)?.let { adapter ->
                    val position = adapter
                            .getItems { PictureLoader.convert(it).convertToUi() }
                            .indexOfFirst { it.getUri() == uri }

                    if (position >= 0) {
                        adapter.notifyItemChanged(position)
                    } else {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
        val filter = IntentFilter().apply {
            addAction(CameraLoader.INTENT_INVALIDATE_GALLERY)
        }
        context.registerReceiver(invalidateReceiver, filter)
    }

    override fun onDetach() {
        selectionHolder?.removeToggleListener(tag)
        context?.unregisterReceiver(invalidateReceiver)
        super.onDetach()
    }

    override fun onDestroy() {
        gallery = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onCursorLoaded(cursor: Cursor?) {
        (list_view.adapter as? PictureAdapter)?.apply {
            changeCursor(cursor)
            loading_view.visibility = View.GONE
        }
    }

    override fun getLifeCycle() = this

    /**
     * Trigger that the Fragment will switch the current [Gallery] with the given one.
     *
     * @param gallery the new [Gallery] that should be initialised.
     */
    fun swap(gallery: Gallery) {
        this.gallery = gallery
        activity?.let {
            loading_view.visibility = View.VISIBLE
            presenter.swap(it, gallery)
        }
    }

    private fun onPictureClick(picture: Picture) {
        gallery?.let { selectionHolder?.toggle(picture.getUri(), it) }
    }
}