package com.lovoo.android.pickpic.instrumentation.helper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.junit.Ignore


/**
 * Created by André Schabrocker on 2019-10-30
 */

object RecyclerViewMatcher {

    @Ignore class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {
        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            check(view is RecyclerView) { "$view has to be a RecyclerView" }
            checkNotNull(view.adapter) { "Adapter not assigned to $view" }
            ViewMatchers.assertThat("RecyclerView item count", view.adapter!!.itemCount, CoreMatchers.equalTo(count))
        }
    }
}
