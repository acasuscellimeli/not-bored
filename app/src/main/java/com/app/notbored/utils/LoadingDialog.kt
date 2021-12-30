package com.app.notbored.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import com.app.notbored.R

class LoadingDialog (private val activity: Activity) {
    private lateinit var dialog:AlertDialog

    @SuppressLint("InflateParams")
    fun showAlertDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)
        dialog.show()
    }

    fun hideAlertDialog(){
        if (this::dialog.isInitialized) {
            dialog.dismiss()
        }
    }
}