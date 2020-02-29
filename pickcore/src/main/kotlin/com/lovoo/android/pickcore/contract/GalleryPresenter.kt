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
package com.lovoo.android.pickcore.contract

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * Contract to implement for Presenter.
 *
 * @see com.lovoo.android.pickcore.presenter.GalleryPresenterImpl
 */
interface GalleryPresenter {

    /**
     * Notify that the Fragment got attached.
     *
     * @param activity the [FragmentActivity] that holds the Fragment
     */
    fun onAttach(activity: FragmentActivity)

    /**
     * Notify that the Fragment is created or restored.
     *
     * @param savedInstanceState the restored [Bundle]
     */
    fun onCreate(savedInstanceState: Bundle?)

    /**
     * Notify that the Fragment got destroyed.
     */
    fun onDestroy()

    /**
     * Start to load all device galleries.
     */
    fun load()
}
