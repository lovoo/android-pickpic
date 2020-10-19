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
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.annotation.DrawableRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickapp.R
import com.lovoo.android.pickapp.adapter.SelectionAdapter
import com.lovoo.android.pickapp.model.Picker
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.pickpic_layout_selectionbar.view.*

/**
 * UI extension to handle the PickPicActivities Selectionbar and RecyclerView.
 * It observes changes emitted by [Picker] and add, removes or select the Thumbnails.
 * You have to call destroy if the UI is removed.
 *
 * The action button text and click handle is customizable via setters.
 *
 * @param picker the Picker instance
 * @param layout the bottom sheet layout.
 * @param dependingViews the views that should dodge the sheet
 */
class Selectionbar(
    private val picker: Picker,
    private val layout: View,
    private val dependingViews: Array<View>
) {

    private val adapter = SelectionAdapter()
    private val subscriptions = CompositeDisposable()
    private var layoutManager: ReverseLayoutManager? = null

    init {
        adapter.onClickListener = { _, picture ->
            picker.select(picture)
        }

        layout.selection_list.let {
            layoutManager = ReverseLayoutManager(it.context, LinearLayoutManager.HORIZONTAL, true)
            it.layoutManager = layoutManager

            it.addItemDecoration(object : RecyclerView.ItemDecoration() {
                var padding: Int = -1
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    if (padding < 0) {
                        padding = view.resources.getDimensionPixelOffset(R.dimen.pickpic_thumbnail_padding_right)
                    }
                    outRect.right = padding
                }
            })

            it.adapter = adapter
        }

        if (Build.VERSION.SDK_INT < 21) {
            // set theme color only on devices without xml tint
            val color = getThemeColor(layout.context)
            var background = layout.selection_button_text.background.apply {
                mutate()
                colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
            }
            ViewCompat.setBackground(layout.selection_button_text, background)

            background = layout.selection_button_icon.background.apply {
                mutate()
                colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
            }
            ViewCompat.setBackground(layout.selection_button_icon, background)
        }

        layout.post {
            // check if no one add a child in the meantime
            if (adapter.itemCount == 0) {
                // set start values for layout animation when layout cycle pass
                layout.alpha = 0f
                layout.y += layout.measuredHeight.toFloat()
                layout.visibility = View.GONE
            }
        }

        registerPicker()
        updateView()
    }

    /**
     * @param text the button label or null
     */
    fun setButtonText(text: String?) {
        if (text.isNullOrEmpty()) {
            layout.selection_button_text.visibility = View.GONE
        } else {
            layout.selection_button_text.text = text
            layout.selection_button_text.visibility = View.VISIBLE
            layout.selection_fade.visibility = View.VISIBLE
            setButtonIcon(null)
        }
    }

    /**
     * @param icon the button icon as ResourceId or null
     */
    fun setButtonIcon(@DrawableRes icon: Int?) {
        if (icon == null) {
            layout.selection_button_icon.visibility = View.GONE
        } else {
            layout.selection_button_icon.setImageResource(icon)
            layout.selection_button_icon.visibility = View.VISIBLE
            layout.selection_fade.visibility = View.VISIBLE
            setButtonText(null)
        }
    }

    /**
     * Set the action for the bottom bar done button.
     *
     * @param listener action that will be triggered onClick or null
     */
    fun setButtonOnClick(listener: (() -> Unit)?) {
        layout.selection_button.setOnClickListener { listener?.invoke() }
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
        adapter.add(uri)
        updateView()

        if (layout.visibility == View.INVISIBLE) {
            // triggered before layout pass --> visible without animation
            layout.visibility = View.VISIBLE
            layout.post { updateDependingViews(layout.measuredHeight) }
        } else if (layout.visibility == View.GONE) {
            layout.animate()
                .alpha(1f)
                .yBy(-layout.measuredHeight.toFloat())
                .setInterpolator(DecelerateInterpolator(2f))
                .withEndAction { updateDependingViews(layout.measuredHeight) }
                .start()
            layout.visibility = View.VISIBLE
        }
    }

    /**
     * Remove the picture from the bottom bar.
     * If the last item was removed the view will be animated out.
     * It will also remove the offset from the depending views.
     *
     * @param uri that should be removed
     */
    private fun removeThumbnail(uri: Uri) {
        adapter.remove(uri)
        updateView()

        if (adapter.itemCount == 0) {
            updateDependingViews(0)
            layout.animate()
                .alpha(0f)
                .yBy(layout.measuredHeight.toFloat())
                .setInterpolator(AccelerateInterpolator(2f))
                .withEndAction { layout.visibility = View.GONE }
                .start()
        }
    }

    private fun scrollTo(position: Int?) {
        position?.let {
            val offset = layout.resources.getDimensionPixelSize(R.dimen.pickpic_thumbnail_size)
            (layout.selection_list.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(it, offset)
        }
    }

    private fun registerPicker() {
        subscriptions.add(
            picker.getObservable().subscribe(
                { state ->
                    when (state) {
                        is Picker.AddState -> addThumbnail(state.uri)
                        is Picker.RemoveState -> removeThumbnail(state.uri)
                        is Picker.SelectionState -> {
                            adapter.selectedUri = state.uri
                            layoutManager?.scrollEnabled = state.uri == null
                            if (state.position != -1) {
                                // scroll to reverse position
                                scrollTo(adapter.itemCount - 1 - state.position)
                            }
                        }
                    }
                },
                { error -> error.printStackTrace() }
            )
        )
    }

    private fun updateView() {
        val res = layout.resources
        val selected = adapter.itemCount
        val requested = picker.config.minCount - selected

        val text = when {
            selected >= picker.config.maxCount -> res.getQuantityString(R.plurals.pickpic_label_selection_max, picker.config.maxCount, picker.config.maxCount)
            requested > 0 -> res.getQuantityString(R.plurals.pickpic_label_selection_more, requested, requested)
            else -> res.getQuantityString(R.plurals.pickpic_label_selection_count, selected, selected)
        }

        layout.selection_text.text = text
        layout.selection_button.isEnabled = requested <= 0 && selected <= picker.config.maxCount
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
