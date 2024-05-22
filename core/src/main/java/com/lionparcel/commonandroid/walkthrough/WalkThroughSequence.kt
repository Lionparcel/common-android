package com.lionparcel.commonandroid.walkthrough

import android.os.Handler
import android.os.Looper

class WalkThroughSequence {

    companion object {
        private const val STAGING_AUTOMATION = "stagingAutomation"
    }

    private val walkThroughBuilderList = ArrayList<WalkThroughBuilder>()
    private var beforeShowingListener: (position: Int, walkThrough: WalkThrough) -> Unit = { _,_ ->

    }
    private var eachSequenceDelay: Long = 0
    private var skipListener: WalkThroughSkipListener? = null
    private var finishListener: WalkThroughFinishListener? = null

    init {
        walkThroughBuilderList.clear()
    }

    fun setSequenceDelay(delay: Long) {
        eachSequenceDelay = delay
    }

    fun addWalkThrough(walkThroughBuilder: WalkThroughBuilder): WalkThroughSequence {
        walkThroughBuilderList.add(walkThroughBuilder)
        return this
    }

    fun addWalkThroughList(walkThroughBuilders: List<WalkThroughBuilder>): WalkThroughSequence {
        walkThroughBuilderList.addAll(walkThroughBuilders)
        return this
    }

    fun replaceWalkThrough(
        position: Int,
        walkThroughBuilder: WalkThroughBuilder
    ): WalkThroughSequence {
        walkThroughBuilderList[position] = walkThroughBuilder
        return this
    }

    fun addBeforeShowingListener(beforeShowingListener: (position: Int, walkThrough: WalkThrough) -> Unit): WalkThroughSequence {
        this.beforeShowingListener = beforeShowingListener
        return this
    }

    fun show(buildFlavor: String): WalkThrough? {
        return if (buildFlavor != STAGING_AUTOMATION) show(0) else null
    }

    private fun show(position: Int): WalkThrough? {
        if (position >= walkThroughBuilderList.size) return null

        val first = 0
        val last = walkThroughBuilderList.size - 1

        if (walkThroughBuilderList.size == 1 && position == 0) {
            walkThroughBuilderList[0].sequencePosition = WalkThrough.SequencePosition.SINGLE
        } else {
            walkThroughBuilderList[position].sequencePosition = when (position) {
                last -> WalkThrough.SequencePosition.LAST
                first -> WalkThrough.SequencePosition.FIRST
                else -> WalkThrough.SequencePosition.MIDDLE
            }
        }
        val walkThrough = walkThroughBuilderList[position].sequenceListener(object :
            WalkThroughSequenceListener {
            override fun onGetCurrentPosition() = position

            override fun onPrevWalkThrough() {
                Handler(Looper.getMainLooper()).postDelayed({
                    val newPosition = position - 1
                    if (newPosition != -1) {
                        show(position - 1)
                    }
                }, eachSequenceDelay)
            }

            override fun onNextWalkThrough() {
                Handler(Looper.getMainLooper()).postDelayed({
                    checkWalkThroughAutoNext(position)
                }, eachSequenceDelay)
            }
        })
            .walkThroughSequenceIndex(position, walkThroughBuilderList.size)
            .skipListener(skipListener)
            .finishListener(finishListener)
            .show()
        beforeShowingListener.invoke(position, walkThrough)
        return walkThrough
    }

    private fun checkWalkThroughAutoNext(position: Int) {
        if (walkThroughBuilderList.size > position + 2 && walkThroughBuilderList[position + 1].autoNext) {
            show(position + 2)
            if (walkThroughBuilderList.size > position + 3 && walkThroughBuilderList[position + 2].autoNext) {
                show(position + 3)
                if (walkThroughBuilderList.size > position + 4 && walkThroughBuilderList[position + 3].autoNext) {
                    show(position + 4)
                    if (walkThroughBuilderList.size > position + 5 && walkThroughBuilderList[position + 4].autoNext) {
                        show(position + 5)
                    }
                }
            }
        } else {
            show(position + 1)
        }
    }

    fun customShow() = showWithCustomIndex(0)

    private fun showWithCustomIndex(position: Int): WalkThrough? {
        if (position >= walkThroughBuilderList.size) return null

        val first = 0
        val last = walkThroughBuilderList.size - 1

        if (walkThroughBuilderList.size == 1 && position == 0) {
            walkThroughBuilderList[0].sequencePosition = WalkThrough.SequencePosition.SINGLE
        } else {
            walkThroughBuilderList[position].sequencePosition = when (position) {
                last -> WalkThrough.SequencePosition.LAST
                first -> WalkThrough.SequencePosition.FIRST
                else -> WalkThrough.SequencePosition.MIDDLE
            }
        }
        val walkThrough = walkThroughBuilderList[position].sequenceListener(object :
            WalkThroughSequenceListener {
            override fun onGetCurrentPosition() = position

            override fun onPrevWalkThrough() {
                Handler(Looper.getMainLooper()).postDelayed({
                    val newPosition = position - 1
                    if (newPosition != -1) {
                        show(position - 1)
                    }
                }, eachSequenceDelay)
            }

            override fun onNextWalkThrough() {
                Handler(Looper.getMainLooper()).postDelayed({
                    checkWalkThroughAutoNext(position)
                }, eachSequenceDelay)
            }
        })
            .skipListener(skipListener)
            .finishListener(finishListener)
            .show()
        beforeShowingListener.invoke(position, walkThrough)
        return walkThrough
    }

    fun skipListener(skip: () -> Unit): WalkThroughSequence {
        this.skipListener = object : WalkThroughSkipListener {
            override fun onSkip() {
                skip.invoke()
            }
        }
        return this
    }

    fun finishListener(finish: () -> Unit): WalkThroughSequence {
        this.finishListener = object : WalkThroughFinishListener {
            override fun onFinish() {
                finish.invoke()
            }
        }
        return this
    }
}
