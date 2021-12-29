package com.app.notbored.utils

import java.util.*

fun String.toCapitalize() : String{
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}