package com.lionparcel.commonandroid.walkthrough

interface WalkThroughMessageViewListener {

    fun onBack() = Unit

    fun onNext() = Unit

    fun onFinish() = Unit

    fun onSkip() = Unit
}
