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

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickapp.R
import com.lovoo.android.pickapp.adapter.SelectionAdapter
import com.lovoo.android.pickapp.databinding.PickpicLayoutSelectionbarBinding
import com.lovoo.android.pickapp.model.Picker
import io.reactivex.disposables.CompositeDisposable

/**
 * UI extension to handle the PickPicActivities Selectionbar and RecyclerView.
 * It observes changes emitted by [Picker] and add, removes or select the Thumbnails.
 * You have to call destroy if the UI is removed.
 *
 * The action button text and click handle is customizable via setters.
 *
 * @param picker the Picker instance
 * @param binding selectionbar binding.
 * @param dependingViews the views that should dodge the sheet
 */
class Selectionbar(
    private val picker: Picker,
    private val binding: PickpicLayoutSelectionbarBinding,
    private val dependingViews: Array<View>,
) {

    private val adapter = SelectionAdapter(picker.config.maxCount)
    private val subscriptions = CompositeDisposable()

    init {
        adapter.onClickListener = { _, picture ->
            picker.select(picture)
        }

        binding.apply {
            selectionList.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(object : RecyclerView.ItemDecoration() {
                    var padding: Int = -1
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        if (padding < 0) {
                            padding = resources.getDimensionPixelOffset(R.dimen.pickpic_thumbnail_padding_right)
                        }
                        outRect.right = padding
                    }
                })
                adapter = this@Selectionbar.adapter
            }

            if (Build.VERSION.SDK_INT < 21) {
                // set theme color only on devices without xml tint
                val color = getThemeColor(root.context)
                var background = selectionButtonText.background.apply {
                    mutate()
                    colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
                }
                ViewCompat.setBackground(selectionButtonText, background)

                background = selectionButtonIcon.background.apply {
                    mutate()
                    colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
                }
                ViewCompat.setBackground(selectionButtonIcon, background)
            }

            root.post {
                updateDependingViews(root.measuredHeight)
            }

            registerPicker()
            updateSelectionText()
        }
    }

    /**
     * @param text the button label or null
     */
    fun setButtonText(text: String?) {
        binding.apply {
            if (text.isNullOrEmpty()) {
                selectionButtonText.visibility = View.GONE
            } else {
                selectionButtonText.text = text
                selectionButtonText.visibility = View.VISIBLE
                selectionFade.visibility = View.VISIBLE
                setButtonIcon(null)
            }
        }
    }

    /**
     * @param icon the button icon as ResourceId or null
     */
    fun setButtonIcon(@DrawableRes icon: Int?) {
        binding.apply {
            if (icon == null) {
                selectionButtonIcon.visibility = View.GONE
            } else {
                selectionButtonIcon.setImageResource(icon)
                selectionButtonIcon.visibility = View.VISIBLE
                selectionFade.visibility = View.VISIBLE
                setButtonText(null)
            }
        }
    }

    /**
     * Set the action for the bottom bar done button.
     *
     * @param listener action that will be triggered onClick or null
     */
    fun setButtonOnClick(listener: (() -> Unit)?) {
        binding.selectionButton.setOnClickListener { listener?.invoke() }
    }

    /**
     * Call it when the ui is detached or destroyed.
     */
    fun destroy() {
        adapter.clear()
        subscriptions.dispose()
    }

    /**
     * Add a picture to the bottom bar.
     * If [Selectionbar] is not visible it will animate from the screen bottom.
     * Depending views will receive a paddingBottom or marginBottom so that the
     * underlying layout will be visible.
     *
     * If layoutBehavior is set the view will receive paddingBottom.
     *
     * @param uri that should be added
     */
    private fun addThumbnail(uri: Uri) {
        val index = adapter.add(uri)
        if (index >= 0) scrollTo(index)
        updateSelectionText()
    }

    /**
     * Remove the picture from the bottom bar.
     * If the last item was removed the view will be animated out.
     * It will also remove the offset from the depending views.
     *
     * @param uri that should be removed
     */
    private fun removeThumbnail(uri: Uri) {
        val index = 0.coerceAtLeast(adapter.remove(uri) - 1)
        scrollTo(index)
        updateSelectionText()
    }

    private fun scrollTo(position: Int?) {
        position?.let {
            val offset = binding.root.resources.getDimensionPixelSize(R.dimen.pickpic_thumbnail_size)
            (binding.selectionList.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(it, offset)
        }
    }

    private fun registerPicker() {
        subscriptions.add(
            picker.getObservable().subscribe(
                { state ->
                    when (state) {
                        is Picker.State.Add -> addThumbnail(state.uri)
                        is Picker.State.Remove -> removeThumbnail(state.uri)
                        is Picker.State.Select -> {
                            adapter.selectedUri = state.uri
                            if (state.position != -1) {
                                scrollTo(state.position)
                            }
                        }
                    }
                },
                { error -> error.printStackTrace() },
            ),
        )
    }

    private fun updateSelectionText() {
        val res = binding.root.resources
        val selected = adapter.getListCount()
        val required = picker.config.minCount - selected

        val text = when {
            required > 0 -> res.getQuantityString(R.plurals.pickpic_label_selection_more, picker.config.minCount, picker.config.minCount)
            else -> res.getString(R.string.pickpic_label_count_of_max_selected, selected, picker.config.maxCount)
        }

        binding.selectionText.text = text
        binding.selectionButton.isEnabled = required <= 0 && selected <= picker.config.maxCount
    }

    private fun updateDependingViews(offset: Int) {
        dependingViews.forEach {
            if ((it.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior != null) {
                // need to set padding because margin breaks the layout behaviour
                it.setPadding(it.paddingLeft, it.paddingTop, it.paddingRight, offset)
            } else {
                (it.layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin = offset
            }
        }
    }

    private fun getThemeColor(context: Context): Int {
        val typedValue = TypedValue()
        val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
        val color = a.getColor(0, 0)
        a.recycle()
        return color
    }
}
