package com.app.notbored.utils

import android.content.Context
import android.widget.Toast
import java.util.*

fun String.toCapitalize() : String{
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()