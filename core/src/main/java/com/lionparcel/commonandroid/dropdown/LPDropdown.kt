package com.lionparcel.commonandroid.dropdown

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutDropdownBinding
import com.lionparcel.commonandroid.dropdown.utils.BaseDropdown

class LPDropdown @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseDropdown(context, attrs, defStyleAttr) {

    override val type: Type
        get() = Type.DEFAULT

}