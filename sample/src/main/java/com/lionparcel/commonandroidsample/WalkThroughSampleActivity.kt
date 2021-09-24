package com.lionparcel.commonandroidsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.card.MaterialCardView
import com.lionparcel.commonandroid.walkthrough.WalkThroughBuilder
import com.lionparcel.commonandroid.walkthrough.WalkThroughSequence

class WalkThroughSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through_sample)
        val mcvContent = findViewById<MaterialCardView>(R.id.mcvContent)
        val btnMock = findViewById<Button>(R.id.btnMock)
        WalkThroughSequence().addWalkThroughList(
            listOf(
                WalkThroughBuilder(this).title("Write Your Title")
                    .description("Write Your Description")
                    .offsetPosition(-10, -10, 10, 10)
                    .targetView(mcvContent).targetArrowView(mcvContent),
                WalkThroughBuilder(this).title("Write Your Title Here (2)")
                    .description("Write Your Description (2)")
                    .targetView(btnMock).targetArrowView(btnMock)
            )
        ).addBeforeShowingListener {
            // add callback before showing the walkthrough
        }.skipListener {
            // add callback when skip/finish the walkthrough
        }.show()
    }
}