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

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.loader.app.LoaderManager
import com.lovoo.android.pickcore.contract.GalleryPresenter
import com.lovoo.android.pickcore.contract.GalleryView
import com.lovoo.android.pickcore.loader.Collector
import com.lovoo.android.pickcore.loader.GalleryLoader

/**
 * Implementation of [GalleryPresenter] for Fragments to load the device galleries.
 * Don't forget to forward these events:
 *  onAttach()
 *  onCreate()
 *  onDestroy()
 *
 * Start loading with load() if you have the permission to access the Filesystem.
 *
 * @param view the contract to the UI Layer
 */
class GalleryPresenterImpl(private val view: GalleryView) : GalleryPresenter {

    private var collector: Collector? = null

    /**
     * Clear the last [Collector] and create a new [Collector] to load the device galleries.
     * Result will be forwarded in [GalleryView].onCursorLoaded([Cursor]?).
     *
     * @param activity the current [FragmentActivity]
     */
    override fun onAttach(activity: FragmentActivity) {
        collector?.onDestroy()
        collector = Collector(LoaderManager.getInstance(activity), GalleryLoader.newInstance(activity), 1).apply {
            cursor.observe(view.getLifeCycle(), Observer { t -> view.onCursorLoaded(t) })
        }
    }

    /**
     * Currently empty implemented.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // no-op
    }

    /**
     * Clear the current [Collector].
     */
    override fun onDestroy() {
        collector?.onDestroy()
    }

    /**
     * Start the loading process if onAttach was called already.
     */
    override fun load() {
        collector?.load()
    }
}
