package com.app.notbored.views
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.app.notbored.R
import com.app.notbored.constants.Constants
import com.app.notbored.databinding.ActivityStartBinding
import com.app.notbored.utils.showSnackbar

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
                if (startButton.isEnabled){
                    startButton.setBackgroundResource(R.drawable.base_button)
                }else{
                    startButton.setBackgroundResource(R.drawable.disabled_base_button)
                }
            }

            startButton.setOnClickListener {
                if (!checkedTermsBox.isChecked){
                    showSnackbar(binding.root, "Please accept the terms and conditions to continue")
                    return@setOnClickListener
                }

                val participants = if(editTextParticipants.text.isEmpty())
                    Constants.PARTICIPANT_DEFAULT_VALUE.toString()
                else
                    editTextParticipants.text.toString()

                val sharedPref = this@StartActivity.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putInt(Constants.PARTICIPANTS, participants.toInt())
                    commit()
                }

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