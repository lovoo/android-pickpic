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

import android.app.Activity
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.lovoo.android.pickapp.R
import com.lovoo.android.pickapp.adapter.PickPicAdapter
import com.lovoo.android.pickapp.factory.GlideEngine
import com.lovoo.android.pickapp.factory.PickDependencies
import com.lovoo.android.pickapp.model.PickPicConfig
import com.lovoo.android.pickapp.model.Picker
import com.lovoo.android.pickcam.view.PickPicCaptureFragment
import com.lovoo.android.pickcore.Constants
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.CameraEngine
import com.lovoo.android.pickcore.contract.CaptureCallback
import com.lovoo.android.pickcore.contract.SelectionHolder
import com.lovoo.android.pickcore.contract.ToggleCallback
import com.lovoo.android.pickcore.engine.DisabledCameraEngine
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickcore.model.convertToUi
import com.lovoo.android.pickcore.permission.Permission
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.pickpic_activity.*
import kotlinx.android.synthetic.main.pickpic_layout_toolbar.*

/**
 * Ready to use PickPic implementation. Configurable with [PickPicConfig].
 */
class PickPicActivity : AppCompatActivity(), SelectionHolder, CameraEngine, CaptureCallback {

    private lateinit var config: PickPicConfig
    private lateinit var picker: Picker
    private val subscriptions = CompositeDisposable()
    private val dependencies = PickDependencies()
    private var externalToggleListener: MutableMap<Any, ToggleCallback> = mutableMapOf()
    private var selectionbar: Selectionbar? = null
    private var preview: Preview? = null

    private var selectedGallery: Gallery? = null
    private var selectedUri: Uri? = null
        set(value) {
            field = value
            updateToolbar()
        }

    init {
        PickPicProvider.apply {
            imageEngine = GlideEngine()
            cameraEngine = this@PickPicActivity
            selectionHolder = this@PickPicActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val default = PickPicConfig(
                minCount = 2,
                maxCount = 10,
                sendText = "DONE",
                title = getString(R.string.pickpic_title),
                header = getString(R.string.pickpic_actionbar_header))

        config = intent?.getParcelableExtra(INTENT_IN_CONFIG) ?: default

        if (config.style != 0) {
            theme.applyStyle(config.style, true)
        }

        setContentView(R.layout.pickpic_activity)

        picker = Picker(Picker.PickConfig(config.minCount, config.maxCount))

        initToolbar()
        initPickerFragments()

        if (config.isPreviewEnabled() || config.maxCount > 1) {
            // initialise selection bar and preview if enabled or multiple pictures can be selected
            initSelectionBar()
            initPreview()
        } else {
            selection_bar.visibility = View.GONE
        }

        if (!config.isPreviewEnabled()) {
            // initialise observer to finish selection when max count is reached
            registerAutoFinish()
        }

        updateToolbar()
        registerPicker()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(BUNDLE_URIS, picker.map.keys.toTypedArray())
        outState.putParcelableArray(BUNDLE_GALLERIES, picker.map.values.toTypedArray())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val deniedPermissions = Permission.getDeniedPermissions(this, Permission.cameraPermissions)
            if (deniedPermissions.isEmpty()) {
                startCamera()
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        try {
            savedInstanceState?.let {
                val uris = it.getParcelableArray(BUNDLE_URIS) as Array<Uri>
                val galleries = it.getParcelableArray(BUNDLE_GALLERIES) as Array<Gallery>
                picker.toggle(uris, galleries)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        if (selectedUri != null) {
            picker.select(null)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        selectionbar?.destroy()
        preview?.destroy()
        subscriptions.dispose()

        PickPicProvider.apply {
            cameraEngine = DisabledCameraEngine()
            selectionHolder = null
        }
        super.onDestroy()
    }

    override fun onCapture(uri: Uri?) {
        if (uri == null) return

        selectedGallery?.let { gallery ->
            toggle(uri, gallery)
        }
    }

    override fun addToggleListener(tag: Any, callback: ToggleCallback) {
        externalToggleListener[tag] = callback
    }

    override fun removeToggleListener(tag: Any) {
        externalToggleListener.remove(tag)
    }

    override fun toggle(uri: Uri, gallery: Gallery) {
        if (!picker.toggle(uri, gallery)) {
            val message = resources.getQuantityString(R.plurals.pickpic_label_selection_max, config.maxCount, config.maxCount)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun isSelected(uri: Uri) = picker.map.containsKey(uri)

    override fun isEnabled(gallery: com.lovoo.android.pickcore.model.GalleryLib): Boolean {
        selectedGallery = gallery.convertToUi()
        return selectedGallery?.name == Constants.All_FOLDER_NAME && dependencies.hasCamera
    }

    override fun getDisplayName(): String = getString(R.string.pickpic_camera_button)

    override fun startCamera() {
        PickPicCaptureFragment.start(supportFragmentManager, CAPTURE_FRAGMENT_TAG)
    }

    private fun registerPicker() {
        subscriptions.add(picker.getObservable().subscribe(
                { state ->
                    when (state) {
                        is Picker.AddState -> externalToggleListener.forEach { (_, callback) ->
                            callback.toggle(state.uri, state.gallery)
                        }
                        is Picker.RemoveState -> externalToggleListener.forEach { (_, callback) ->
                            callback.toggle(state.uri, state.gallery)
                        }
                        is Picker.SelectionState -> selectedUri = state.uri
                    }
                },
                { error -> error.printStackTrace() }
        ))
    }

    private fun registerAutoFinish() {
        subscriptions.add(picker.getObservable().subscribe(
                {
                    if (it is Picker.AddState && picker.getPickedUris().size >= config.maxCount) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            finishSelection()
                        }, AUTO_FINISH_DELAY)
                    }
                },
                { /* no-op */ }
        ))
    }

    private fun initToolbar() {
        toolbar.let {
            it.title = config.title
            it.inflateMenu(R.menu.menu_pickpic)
            it.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.pickpic_menu_faq -> onFaqClick()
                    R.id.pickpic_menu_remove -> {
                        selectedUri?.let {
                            return@setOnMenuItemClickListener picker.remove(it)
                        } ?: return@setOnMenuItemClickListener false
                    }
                    else -> false
                }
            }
            it.setNavigationOnClickListener { onBackPressed() }
            it.navigationIcon = it.navigationIcon?.mutate()?.apply {
                val color = ContextCompat.getColor(it.context, R.color.pickpic_text_actionbar)
                setColorFilter(color, PorterDuff.Mode.SRC_IN)
            }
        }
        toolbar_text.let {
            if (config.header.isNullOrEmpty()) {
                it.visibility = View.GONE
            } else {
                it.text = config.header
                it.visibility = View.VISIBLE
            }
        }
    }

    private fun updateToolbar() {
        toolbar?.menu?.let {
            it.findItem(R.id.pickpic_menu_faq)?.isVisible = selectedUri == null && !config.faqUrl.isNullOrEmpty()
            it.findItem(R.id.pickpic_menu_remove)?.isVisible = selectedUri != null
        }

        // hide tab layout when preview picture is selected or adapter has only one item
        tab_layout?.visibility = if (selectedUri == null && fragment_pager.adapter?.count ?: 0 > 1) View.VISIBLE else View.GONE
    }

    private fun initPickerFragments() {
        fragment_pager.adapter = PickPicAdapter(this, supportFragmentManager, dependencies)

        // hide tab layout when adapter has only one item
        if (fragment_pager.adapter?.count ?: 0 <= 1) {
            tab_layout.visibility = View.GONE

            val paddingBottom = resources.getDimensionPixelOffset(R.dimen.pickpic_actionbar_headline_margin)
            toolbar_text.setPadding(
                    toolbar_text.paddingStart,
                    toolbar_text.paddingTop,
                    toolbar_text.paddingEnd,
                    paddingBottom)

            val mode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
            (toolbar.layoutParams as CollapsingToolbarLayout.LayoutParams).collapseMode = mode
        } else {
            tab_layout.setupWithViewPager(fragment_pager)
        }
    }

    private fun initSelectionBar() {
        val selectionView = findViewById<ViewGroup>(R.id.selection_bar)
        selectionbar = Selectionbar(
                picker,
                selectionView,
                arrayOf(fragment_pager, preview_pager)).apply {

            setButtonText(this@PickPicActivity.config.sendText)
            setButtonIcon(this@PickPicActivity.config.sendIcon)
            setButtonOnClick { finishSelection() }
        }
    }

    private fun initPreview() {
        preview = Preview(preview_pager, supportFragmentManager, picker)
    }

    private fun finishSelection() {
        val list = ArrayList<Uri>(picker.getPickedUris().map { it })
        val data = Intent().apply {
            putParcelableArrayListExtra(INTENT_OUT_DATA, list)
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    private fun onFaqClick(): Boolean {
        val url = config.faqUrl
        if (url.isNullOrEmpty()) return false

        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        return true
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 2735
        private const val AUTO_FINISH_DELAY = 300L
        private const val BUNDLE_URIS = "pickpic_uries"
        private const val BUNDLE_GALLERIES = "pickpic_galleries"
        private const val CAPTURE_FRAGMENT_TAG = "pickpic_capture_tag"

        private const val INTENT_IN_CONFIG = "intent_config"
        private const val INTENT_OUT_DATA = "intent_picker_selection"

        /**
         * Apply the [PickPicConfig] to the [Intent] for [PickPicActivity].
         *
         * @param intent the used [Intent]
         * @param config the desired configuration
         */
        fun applyConfig(intent: Intent, config: PickPicConfig) {
            intent.putExtra(INTENT_IN_CONFIG, config)
        }

        /**
         * Receive the [Uri] [List] result from the [Intent].
         *
         * @param data the [Intent] that was passed as Activity result
         * @return [List] of [Uri]s or null
         */
        fun getResult(data: Intent?): List<Uri>? {
            return data?.getParcelableArrayListExtra(INTENT_OUT_DATA)
        }
    }
}
