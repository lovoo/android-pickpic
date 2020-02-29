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
package com.lovoo.android.pickcore.presenter

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.loader.app.LoaderManager
import com.lovoo.android.pickcore.contract.PicturePresenter
import com.lovoo.android.pickcore.contract.PictureView
import com.lovoo.android.pickcore.loader.Collector
import com.lovoo.android.pickcore.loader.PictureLoader
import com.lovoo.android.pickcore.model.Gallery
import com.lovoo.android.pickcore.model.convertToLib

/**
 * Implementation of [PicturePresenter] for Fragments to load the pictures from a [Gallery].
 * Don't forget to forward onDestroy().
 *
 * Start loading with swap().
 *
 * @param view the contract to the UI Layer
 */
class PicturePresenterImpl(val view: PictureView) : PicturePresenter {

    private var collector: Collector? = null

    /**
     * Clear the last [Collector] and and creates a new [Collector] to load the device pictures from gallery.
     * Result will be forwarded in [PictureView].onCursorLoaded([Cursor]?).
     *
     * @param activity the current [FragmentActivity]
     * @param gallery the current selected [Gallery]
     */
    override fun swap(activity: FragmentActivity, gallery: Gallery) {
        collector?.onDestroy()
        collector = Collector(LoaderManager.getInstance(activity), PictureLoader.newInstance(activity, gallery.convertToLib()), 2).apply {
            cursor.observe(view.getLifeCycle(), Observer { t -> view.onCursorLoaded(t) })
            load()
        }
    }

    /**
     * Clear the current [Collector].
     */
    override fun onDestroy() {
        collector?.onDestroy()
    }
}
