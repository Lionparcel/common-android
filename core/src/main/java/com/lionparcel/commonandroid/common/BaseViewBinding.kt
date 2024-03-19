package com.lionparcel.commonandroid.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface BaseViewBinding<B : ViewBinding> {

    var binding: B

    fun getViewBinding(inflater: LayoutInflater, container: ViewGroup? = null): B
}