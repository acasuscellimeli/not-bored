package com.app.notbored.views

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.app.notbored.R
import com.app.notbored.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurateView()
    }

    private fun configurateView() {
        with(binding) {
            editTextParticipants.addTextChangedListener {
                startButton.isEnabled = !(it.toString().contains("." ) || it.toString().contains(","))

            }

            startButton.setOnClickListener {
                val intent = Intent(this@StartActivity,ActivitiesActivity::class.java)
                startActivity(intent)
            }

            textViewTerms.setOnClickListener {
                val intent = Intent(this@StartActivity,TermsAndConditionsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}