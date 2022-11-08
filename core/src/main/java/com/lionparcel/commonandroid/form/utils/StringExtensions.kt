package com.lionparcel.commonandroid.form.utils

import java.util.*

fun String.toTitleCase() : String {
    if (this.isEmpty()) return this
    // use US locale for lower/upper case, then remove duplicate spaces, trim them, then rejoin them with transform
    return lowercase(Locale.US).replace(Regex("\\s+"), " ").trim().split(" ").joinToString(" ") {
        if (it.first() in 'a'..'z') {
            it.capitalize()
        } else {
            if (!it.contains("(") || !it.contains(")")) {
                "${it.first()}${it.substring(1).capitalize()}"
            } else it.uppercase(Locale.US)
        }
    }
}