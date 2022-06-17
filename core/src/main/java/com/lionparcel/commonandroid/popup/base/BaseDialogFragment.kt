@file:Suppress("DEPRECATION")

package com.lionparcel.commonandroid.popup.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lionparcel.commonandroid.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDialogFragment : DialogFragment() {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun getContentResource(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
        return inflater.inflate(getContentResource(), container, false)
    }

    protected open fun initViews() = Unit

    protected fun Disposable.collect() = compositeDisposable.add(this)

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }
}