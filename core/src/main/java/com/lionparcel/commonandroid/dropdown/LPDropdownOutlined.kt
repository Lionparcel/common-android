package com.lionparcel.commonandroid.dropdown

import android.content.Context
import android.util.AttributeSet
import com.lionparcel.commonandroid.dropdown.utils.BaseDropdown

class LPDropdownOutlined @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseDropdown(context, attrs, defStyleAttr) {

    override val type: Type
        get() = Type.OUTLINED

}