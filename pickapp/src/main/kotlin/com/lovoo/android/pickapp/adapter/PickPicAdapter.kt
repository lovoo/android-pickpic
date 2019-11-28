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
        private val dependencies: PickDependencies
) : FragmentStatePagerAdapter(manager) {

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