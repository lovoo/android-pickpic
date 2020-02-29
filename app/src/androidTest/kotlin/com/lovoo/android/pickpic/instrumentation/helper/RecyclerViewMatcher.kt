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
