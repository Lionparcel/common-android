package com.lionparcel.commonandroid.modal

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun showCustomModal(
    fragmentManager: FragmentManager,
    key: String,
    dialogFragment: DialogFragment
) {
    fragmentManager.let {
        dialogFragment.show(it, key)
    }
}