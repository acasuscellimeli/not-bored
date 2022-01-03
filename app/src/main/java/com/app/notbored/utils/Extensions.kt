package com.app.notbored.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.notbored.R
import com.app.notbored.databinding.CustomSnackbarBinding
import com.google.android.material.snackbar.Snackbar
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

fun AppCompatActivity.showSnackbar(view: View, text:String, isRedAlert:Boolean = false){
    val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    val binding = CustomSnackbarBinding.inflate(layoutInflater)

    with(binding){
        snackBarTittle.text = text
        snackBarTittle.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        cardContainerSnackView.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.dark_gray))

        if (isRedAlert){
            cardContainerSnackView.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.red))
        }
    }

    snackbar.view.setBackgroundColor(Color.TRANSPARENT)
    val snackLayout = snackbar.view as Snackbar.SnackbarLayout
    snackLayout.setPadding(0, 0, 0, 0)
    snackLayout.addView(binding.root, 0)
    snackbar.show()
}