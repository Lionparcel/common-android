package com.lionparcel.commonandroid.popup

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun showCustomPopup(
    fragmentManager: FragmentManager,
    key: String,
    dialogFragment: DialogFragment
) {
    fragmentManager.let {
        dialogFragment.show(it, key)
    }
}