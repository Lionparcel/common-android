@file:Suppress("DEPRECATION")

package com.lionparcel.commonandroid.modal.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lionparcel.commonandroid.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseSheetDialogFragment : BottomSheetDialogFragment() {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun getContentResource(): Int

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getContentResource(), container, false)
    }

    protected fun getLayoutBehaviour(): BottomSheetBehavior<FrameLayout> {
        val bottomSheet = (dialog as BottomSheetDialog).findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
        bottomSheet.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        return BottomSheetBehavior.from<FrameLayout>(bottomSheet)
    }

    @CallSuper
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
