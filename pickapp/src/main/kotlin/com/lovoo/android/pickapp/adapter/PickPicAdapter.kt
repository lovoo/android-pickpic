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
package com.lovoo.android.pickapp.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lovoo.android.pickapp.R
import com.lovoo.android.pickapp.factory.PickDependencies
import com.lovoo.android.pickapp.model.PickType
import com.lovoo.android.pickfacebook.view.FbGalleryFragment
import com.lovoo.android.pickui.view.GalleryFragment

/**
 * [FragmentStatePagerAdapter] that contains [GalleryFragment] and optional [FbGalleryFragment].
 *
 * @param context the app context
 * @param manager the [FragmentManager] that should be used
 * @param dependencies [PickDependencies] instance to decide if [FbGalleryFragment] should be added or not
 */
class PickPicAdapter(
    private val context: Context,
    manager: FragmentManager,
    private val dependencies: PickDependencies,
) : FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = buildFragment(getType(position))

    override fun getCount() = when {
        dependencies.hasFacebook -> 2
        else -> 1
    }

    override fun getPageTitle(position: Int): String = when (position) {
        0 -> context.getString(R.string.pickpic_tab_gallery)
        1 -> context.getString(R.string.pickpic_tab_facebook)
        else -> throw IllegalArgumentException("only $count items supported")
    }

    private fun getType(index: Int) = when (index) {
        0 -> PickType.GALLERY
        1 -> PickType.FACEBOOK
        else -> throw IllegalArgumentException("only $count items supported")
    }

    private fun buildFragment(type: PickType) = when (type) {
        PickType.GALLERY -> GalleryFragment.newInstance()
        PickType.FACEBOOK -> FbGalleryFragment.newInstance()
    }
}
