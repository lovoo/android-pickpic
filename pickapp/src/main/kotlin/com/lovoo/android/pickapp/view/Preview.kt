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

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.lovoo.android.pickapp.model.Picker
import io.reactivex.disposables.CompositeDisposable

/**
 * UI extension to handle PickPicActivities Preview ViewPager.
 * It observes [Picker.State] changes and show, update or hide the Preview.
 * You have to call destroy if the UI is removed.
 *
 * @param pager the view pager from the layout
 * @param fragmentManager the FragmentManager
 * @param picker the Picker that will be observed
 */
class Preview(
    val pager: ViewPager,
    val fragmentManager: FragmentManager,
    val picker: Picker
) {

    private val subscriptions = CompositeDisposable()

    init {
        registerPicker()
    }

    /**
     * Call it when the ui is detached or destroyed.
     */
    fun destroy() {
        subscriptions.dispose()
    }

    private fun registerPicker() {
        subscriptions.add(
            picker.getObservable().subscribe(
                { state ->
                    when (state) {
                        is Picker.State.Add -> {
                            // no-op
                        }
                        is Picker.State.Remove -> {
                            pager.adapter?.let {
                                it.notifyDataSetChanged()
                                picker.select(Math.min(pager.currentItem, it.count - 1))
                            }
                        }
                        is Picker.State.Select -> {
                            if (state.uri == null) {
                                hidePreview()
                            } else {
                                if (pager.adapter == null) {
                                    showPreview()
                                }
                                updatePreview(state.position)
                            }
                        }
                    }
                },
                { error -> error.printStackTrace() }
            )
        )
    }

    private fun showPreview() {
        pager.let {
            it.adapter = object : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(position: Int) = PreviewFragment.createInstance(
                    position,
                    picker.getPickedUris().getOrNull(position)
                )

                override fun getCount() = picker.map.size

                override fun getItemPosition(fragment: Any) = PagerAdapter.POSITION_NONE
            }
            it.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                private var wasDragged = false
                override fun onPageScrollStateChanged(state: Int) {
                    if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                        wasDragged = true
                    }
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    // no-op
                }

                override fun onPageSelected(position: Int) {
                    if (wasDragged) {
                        picker.select(position)
                        wasDragged = false
                    }
                }
            })
            it.visibility = View.VISIBLE
            it.animate().alpha(1f)
        }
    }

    private fun hidePreview() {
        pager.let {
            it.animate().alpha(0f).withEndAction { it.visibility = View.GONE }
            it.clearOnPageChangeListeners()
            it.adapter = null
        }
    }

    private fun updatePreview(position: Int) {
        pager.currentItem = position
    }
}
