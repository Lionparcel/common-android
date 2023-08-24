package com.lionparcel.commonandroid.dropdown

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.TextViewCompat
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutDropdownDaBinding
import com.lionparcel.commonandroid.dropdown.utils.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.lp_layout_dropdown.view.*

class LPDropdownDA @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        const val BUNDLE_INSTANCE = "BUNDLE_INSTANCE"
        const val BUNDLE_CURRENT_POSITION = "BUNDLE_CURRENT_EDIT"
    }

    private val binding: LpLayoutDropdownDaBinding =
        LpLayoutDropdownDaBinding.inflate(LayoutInflater.from(context), this, true)

    var onItemSelectedListener: AdapterView.OnItemSelectedListener? = null

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val mutableList = mutableListOf<DropdownData>()

    private var currentPosition: Int = -1

    private var isCollapsed: Boolean = true

    private var iconStart: Int? = null

    var fireValidation: (() -> Unit)? = null

    private val adapter = DropdownAdapterDA(context, mutableList) {
        getSpinner().selectedItemPosition
    }

    init {
        binding.root
        context.obtainStyledAttributes(attrs, R.styleable.LPDropdownDA).apply {
            try {
                setHintText(getResourceId(R.styleable.LPDropdownDA_android_hint, 0))
                iconStart = getResourceId(R.styleable.LPDropdownDA_android_drawableStart, 0)
            } finally {
                recycle()
            }
        }
        getSpinner().adapter = adapter
        setStartDrawable()
        getInputLayout().endIconDrawable =
            ContextCompat.getDrawable(context, R.drawable.ics_o_chevron_down)
        changeIcon()
    }

    fun setData(data: List<DropdownData>) {
        mutableList.clear()
        mutableList.addAll(data)
        mutableList.add(data.size, DropdownData(""))
        getSpinner().setSelection(if (currentPosition > -1) currentPosition else mutableList.lastIndex)
        adapter.notifyDataSetChanged()
    }

    fun getText() = getEditText().text.toString()

    fun getEditText() = binding.lpActDropdown

    fun getInputLayout() = binding.lpDropdownTextInputLayout

    fun getSpinner(): LPCustomSpinner = binding.lpDropdownSpinner

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
        RxTextView.afterTextChangeEvents(getEditText()).skipInitialValue()
            .doOnError { it.printStackTrace() }
            .doOnNext { getInputLayout().isSelected = !it.editable().isNullOrEmpty() }
            .subscribe().collect()
        RxAdapterView.itemSelections(getSpinner())
            .skipInitialValue()
            .subscribe({
                if (it < mutableList.size - 1 && !mutableList[it].isDisable) {
                    onItemSelectedListener?.onItemSelected(null, null, it, 0L)
                }
                if (!mutableList[it].isDisable) {
                    setText(mutableList[it].text)
                }
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
        getEditText().setText(s)
        getInputLayout().isSelected = !s.isNullOrEmpty()
    }

    private fun setHintText(stringRes: Int) {
        if (stringRes != 0 && !isInEditMode) {
            getEditText().hint = context.getString(stringRes)
        }
    }

    private fun Disposable.collect() = compositeDisposable.add(this)

    private fun changeIcon() {
        getSpinner().setSpinnerEventsListener(object : OnSpinnerEventsListener {
            override fun onSpinnerOpened(spinner: Spinner) {
                getInputLayout().endIconDrawable =
                    ContextCompat.getDrawable(context, R.drawable.ics_o_chevron_up)
            }

            override fun onSpinnerClosed(spinner: Spinner) {
                getInputLayout().endIconDrawable =
                    ContextCompat.getDrawable(context, R.drawable.ics_o_chevron_down)
            }

        })
    }

    private fun setStartDrawable() {
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(
            getEditText(),
            iconStart ?: 0,
            0,
            0,
            0
        )
    }
}