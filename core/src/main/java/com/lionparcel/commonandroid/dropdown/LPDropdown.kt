package com.lionparcel.commonandroid.dropdown

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.dropdown.utils.DropdownAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.lp_layout_dropdown.view.*

class LPDropdown @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        const val BUNDLE_INSTANCE = "BUNDLE_INSTANCE"
        const val BUNDLE_CURRENT_POSITION = "BUNDLE_CURRENT_EDIT"
    }

    var onItemSelectedListener: AdapterView.OnItemSelectedListener? = null

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val mutableList = mutableListOf<Any?>()

    private var currentPosition: Int = -1

    var fireValidation: (() -> Unit)? = null

    private val adapter = DropdownAdapter(context, mutableList) {
        lpDropdownSpinner.selectedItemPosition
    }

    init {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_dropdown, this, true)
        context.obtainStyledAttributes(attrs, R.styleable.LPDropdown).apply {
            try {
                setHintText(getResourceId(R.styleable.LPDropdown_android_hint, 0))
            } finally {
                recycle()
            }
        }
        lpDropdownSpinner.adapter = adapter
    }

    fun <T> setData(data: List<T>) {
        mutableList.clear()
        mutableList.addAll(data)
        mutableList.add(data.size, null)
        lpDropdownSpinner.setSelection(if (currentPosition > -1) currentPosition else mutableList.lastIndex)
        adapter.notifyDataSetChanged()
    }

    fun getText() = lpActDropdown.text.toString()

    fun getEditText() = lpActDropdown

    fun getInputLayout() = lpDropdownTextInputLayout

    fun getSpinner(): Spinner? = lpDropdownSpinner

    override fun onSaveInstanceState(): Parcelable? = bundleOf(
        BUNDLE_INSTANCE to super.onSaveInstanceState(),
        BUNDLE_CURRENT_POSITION to lpDropdownSpinner.selectedItemPosition
    )

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            currentPosition = state.getInt(BUNDLE_CURRENT_POSITION)
            return super.onRestoreInstanceState(state.getParcelable(BUNDLE_INSTANCE))
        }
        super.onRestoreInstanceState(state)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        RxTextView.afterTextChangeEvents(lpActDropdown).skipInitialValue()
            .doOnError { it.printStackTrace() }
            .doOnNext { lpDropdownTextInputLayout.isSelected = !it.editable().isNullOrEmpty() }
            .subscribe().collect()
        RxAdapterView.itemSelections(lpDropdownSpinner)
            .skipInitialValue()
            .subscribe({
                if (it < mutableList.size - 1) {
                    onItemSelectedListener?.onItemSelected(null, null, it, 0L)
                }
                setText(mutableList[it]?.toString() ?: "")
            }, {
                it.printStackTrace()
            })
            .collect()
    }

    override fun onDetachedFromWindow() {
        compositeDisposable.dispose()
        super.onDetachedFromWindow()
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        fireValidation?.let {
            if (!hasWindowFocus) it()
        }
    }

    private fun setText(s: CharSequence?) {
        if (isInEditMode) return
        lpActDropdown.setText(s)
        lpDropdownTextInputLayout.isSelected = !s.isNullOrEmpty()
    }

    private fun setHintText(stringRes: Int) {
        if (stringRes != 0 && !isInEditMode) {
            getInputLayout().hint = context.getString(stringRes)
        }
    }

    private fun Disposable.collect() = compositeDisposable.add(this)
}