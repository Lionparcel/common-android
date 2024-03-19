package com.lionparcel.commonandroid.dropdown.utils

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.TextViewCompat
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutDropdownBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDropdown @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    enum class Type(
        val styleableId: IntArray,
        val hint: Int,
        val drawableStart: Int
    ) {
        DEFAULT(
            R.styleable.LPDropdown,
            R.styleable.LPDropdown_android_hint,
            R.styleable.LPDropdown_android_drawableStart
        ),
        OUTLINED(
            R.styleable.LPDropdownOutlined,
            R.styleable.LPDropdownOutlined_android_hint,
            R.styleable.LPDropdownOutlined_android_drawableStart
        )
    }

    companion object {
        const val BUNDLE_INSTANCE = "BUNDLE_INSTANCE"
        const val BUNDLE_CURRENT_POSITION = "BUNDLE_CURRENT_EDIT"
    }

    private val binding: LpLayoutDropdownBinding =
        LpLayoutDropdownBinding.inflate(LayoutInflater.from(context), this, true)

    var onItemSelectedListener: AdapterView.OnItemSelectedListener? = null

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val mutableList = mutableListOf<Any?>()

    private var currentPosition: Int = -1

    private var isCollapsed: Boolean = true

    private var iconStart: Int? = null

    var fireValidation: (() -> Unit)? = null

    private val adapter = DropdownAdapter(context, mutableList) {
        getSpinner().selectedItemPosition
    }

    init {
        binding.root
        context.obtainStyledAttributes(attrs, type.styleableId).apply {
            try {
                setHintText(getResourceId(type.hint, 0))
                iconStart = getResourceId(type.drawableStart, 0)
            } finally {
                recycle()
            }
        }
        setTextInputLayoutStyle()
        getSpinner().adapter = adapter
        setStartDrawable()
        getInputLayout().endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ics_o_chevron_down)
        changeIcon()
    }

    abstract val type: Type

    fun <T> setData(data: List<T>) {
        mutableList.clear()
        mutableList.addAll(data)
        mutableList.add(data.size, null)
        getSpinner().setSelection(if (currentPosition > -1) currentPosition else mutableList.lastIndex)
        adapter.notifyDataSetChanged()
    }

    fun getText() = getEditText().text.toString()

    fun getEditText() = when (type) {
        Type.DEFAULT -> binding.lpActDropdown
        Type.OUTLINED -> binding.lpActDropdownOutlined
    }

    fun getInputLayout() = when (type) {
        Type.DEFAULT -> binding.lpDropdownTextInputLayout
        Type.OUTLINED -> binding.lpDropdownTextInputLayoutOutlined
    }

    fun getSpinner(): LPCustomSpinner = binding.lpDropdownSpinner

    override fun onSaveInstanceState(): Parcelable? = bundleOf(
        BUNDLE_INSTANCE to super.onSaveInstanceState(),
        BUNDLE_CURRENT_POSITION to binding.lpDropdownSpinner.selectedItemPosition
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
        getEditText().setText(s)
        getInputLayout().isSelected = !s.isNullOrEmpty()
    }

    private fun setHintText(stringRes: Int) {
        if (stringRes != 0 && !isInEditMode) {
            when (type) {
                Type.DEFAULT -> {
                    getInputLayout().hint = context.getString(stringRes)
                }
                Type.OUTLINED -> {
                    getEditText().hint = context.getString(stringRes)
                }
            }
        }
    }

    private fun Disposable.collect() = compositeDisposable.add(this)

    private fun changeIcon() {
        getSpinner().setSpinnerEventsListener(object : OnSpinnerEventsListener{
            override fun onSpinnerOpened(spinner: Spinner) {
                getInputLayout().endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ics_o_chevron_up)
            }

            override fun onSpinnerClosed(spinner: Spinner) {
                getInputLayout().endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ics_o_chevron_down)
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

    private fun setTextInputLayoutStyle() {
        when (type) {
            Type.DEFAULT -> {
                binding.lpDropdownTextInputLayout.visibility = View.VISIBLE
                binding.lpDropdownTextInputLayoutOutlined.visibility = View.GONE
            }
            Type.OUTLINED -> {
                binding.lpDropdownTextInputLayout.visibility = View.GONE
                binding.lpDropdownTextInputLayoutOutlined.visibility = View.VISIBLE
            }
        }
    }
}