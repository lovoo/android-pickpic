package com.lovoo.android.pickpic

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lovoo.android.pickapp.model.PickPicConfig
import com.lovoo.android.pickapp.view.PickPicActivity
import com.lovoo.android.pickui.view.PictureDecoration
import kotlinx.android.synthetic.main.activity_example.*


/**
 * Demo Activity to show how to use PickPic
 */
class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_example)

        button.setOnClickListener { view ->
            // create an Intent, apply the configuration and start for result
            Intent(view.context, PickPicActivity::class.java).let {
                val config = PickPicConfig(
                        style = R.style.ExampleAppTheme_NoActionbar,
                        minCount = 2,
                        maxCount = 10,
                        sendIcon = R.drawable.ic_send,
                        header = getString(R.string.pickpic_actionbar_header),
                        title = getString(R.string.pickpic_title)
                )
                // pass the config to PickPic
                PickPicActivity.applyConfig(it, config)
                // start PickPic and wait for result
                startActivityForResult(it, REQUEST_CODE)
            }
        }

        result_list.let {
            it.layoutManager = LinearLayoutManager(it.context)
            it.addItemDecoration(ExampleDecorator())
            it.adapter = ExampleAdapter(it.context)
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
        (result_list?.adapter as? ExampleAdapter)?.list = result
    }

    companion object {
        // random constant for activity result
        private const val REQUEST_CODE = 45672
    }
}
