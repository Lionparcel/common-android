package com.lionparcel.commonandroid.modal

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun showCustomDialog(
    fragmentManager: FragmentManager,
    key: String,
    dialogFragment: DialogFragment
) {

    var dialogFragmentMap = kotlin.collections.mutableMapOf<kotlin.String, java.lang.ref.WeakReference<androidx.fragment.app.DialogFragment>>()
    fragmentManager.let { manager ->
        dialogFragmentMap[key]?.get()?.dismissAllowingStateLoss()
        dialogFragmentMap[key] = java.lang.ref.WeakReference(dialogFragment)
        dialogFragmentMap[key]?.get()?.show(manager, key)
    }
}