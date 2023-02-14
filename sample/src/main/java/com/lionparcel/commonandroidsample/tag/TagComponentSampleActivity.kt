package com.lionparcel.commonandroidsample.tag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.tag.LPTag
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.tag.utils.TagViewAdapter

class TagComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_component_sample)

        var clicked = 0

        val tag = findViewById<LPTag>(R.id.lp_tag)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_tag)
        val recyclerViewNoIcon = findViewById<RecyclerView>(R.id.rv_tag_no_icon)
        val adapter = TagViewAdapter(arrayListOf("Paket untukmu","Paket untukmu", "Paket untukmu", "Paket untukmu"), true, arrayListOf(false, false, false, false))
        val adapter1 = TagViewAdapter(arrayListOf("Paket untukmu","Paket untukmu", "Paket untukmu", "Paket untukmu"), false, arrayListOf(false, false, false, false))

        tag.titleText("Paket untukmu")
        tag.setIconVisibility(true)
        tag.setOnClickListener {
            tag.tagStatus(!tag.isSelected)
            when (tag.isSelected) {
                false -> {
                    tag.titleText("Paket untukmu")
                }
                true -> {
                    tag.titleText("Paket kirimanmu")
                }
            }
        }

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)

        recyclerViewNoIcon.adapter = adapter1
    }
}
