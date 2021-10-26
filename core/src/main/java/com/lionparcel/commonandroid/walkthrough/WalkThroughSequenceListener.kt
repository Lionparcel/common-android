package com.lionparcel.commonandroid.walkthrough

interface WalkThroughSequenceListener {

    fun onGetCurrentPosition(): Int

    fun onPrevWalkThrough()

    fun onNextWalkThrough()
}
