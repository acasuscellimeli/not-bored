package com.app.notbored.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.notbored.R
import com.app.notbored.databinding.ActivityTermsAndConditionsBinding

class TermsAndConditionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTermsAndConditionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolBar()
    }

    private fun setUpToolBar() {
        with(binding){
            toolBar.icLeft.visibility = View.GONE
            toolBar.toolbarTittle.text=getString(R.string.terms_and_conditions)
            toolBar.icRight.setOnClickListener {
                onBackPressed()
            }
        }
    }
}