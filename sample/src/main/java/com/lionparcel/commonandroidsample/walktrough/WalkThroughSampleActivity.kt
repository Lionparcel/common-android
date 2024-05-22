package com.lionparcel.commonandroidsample.walktrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.card.MaterialCardView
import com.lionparcel.commonandroid.walkthrough.WalkThrough
import com.lionparcel.commonandroid.walkthrough.WalkThroughBuilder
import com.lionparcel.commonandroid.walkthrough.WalkThroughSequence
import com.lionparcel.commonandroidsample.R

class WalkThroughSampleActivity : AppCompatActivity() {

    private var walkThrough : WalkThrough? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through_sample)
        val mcvContent = findViewById<MaterialCardView>(R.id.mcvContent)
        val btnMock = findViewById<Button>(R.id.btnMock)
        val scale = resources.displayMetrics.density
        walkThrough = WalkThroughSequence().addWalkThroughList(
            listOf(
                WalkThroughBuilder(this).title("Write Your Title")
                    .description("Write Your Description")
                    // In CA use Int.toDp() in IntExtension.kt for customRoundedCorner value
                    .customRoundedCorner((8 * scale + 0.5F).toInt())
                    .offsetPosition(-10, -10, 10, 10)
                    .targetView(mcvContent).targetArrowView(mcvContent),
                WalkThroughBuilder(this).title("Write Your Title Here (2)")
                    .description("Write Your Description (2)")
                    .targetView(btnMock).targetArrowView(btnMock)
            )
        ).addBeforeShowingListener { _, wt ->
            walkThrough = wt
        }.show(String())
    }
}
