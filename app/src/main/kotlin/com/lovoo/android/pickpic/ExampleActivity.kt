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
package com.lovoo.android.pickpic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lovoo.android.pickapp.model.PickPicConfig
import com.lovoo.android.pickapp.view.PickPicActivity
import com.lovoo.android.pickpic.databinding.ActivityExampleBinding

/**
 * Demo Activity to show how to use PickPic
 */
class ExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { view ->
            // create an Intent, apply the configuration and start for result
            Intent(view.context, PickPicActivity::class.java).let {
                val config = PickPicConfig(
                    style = R.style.ExampleAppTheme_NoActionbar,
                    minCount = 2,
                    maxCount = 10,
                    sendIcon = R.drawable.ic_send,
                    header = getString(R.string.pickpic_actionbar_header),
                    title = getString(R.string.pickpic_title),
                )
                // pass the config to PickPic
                PickPicActivity.applyConfig(it, config)
                // start PickPic and wait for result
                startActivityForResult(it, REQUEST_CODE)
            }
        }

        binding.resultList.apply {
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(ExampleDecorator())
            adapter = ExampleAdapter(this.context)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }

        val result = PickPicActivity.getResult(data) ?: emptyList()
        if (result.isEmpty()) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
        (binding.resultList.adapter as? ExampleAdapter)?.list = result
    }

    companion object {
        // random constant for activity result
        private const val REQUEST_CODE = 45672
    }
}
