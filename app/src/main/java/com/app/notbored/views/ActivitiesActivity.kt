package com.app.notbored.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.notbored.R
import com.app.notbored.constants.Constants
import com.app.notbored.recyclerView.ActivityAdapter
import com.app.notbored.databinding.ActivityActivitiesBinding
import com.app.notbored.service.APIServiceBored
import com.app.notbored.service.ActivityResponse
import com.app.notbored.service.ActivitySevice
import com.app.notbored.utils.LoadingDialog
import com.app.notbored.utils.showSnackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class ActivitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActivitiesBinding
    private lateinit var adapter: ActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerViewInit()
        setUpToolBar()
    }

    /**
     * Method too set up tool bar elements , tittle and button visibilities and aspects
     */
    private fun setUpToolBar() {
        with(binding) {
            toolBar.icLeft.setOnClickListener {
                onBackPressed()
            }
            toolBar.toolbarTittle.text = getString(R.string.activities)
            toolBar.icRight.setBackgroundResource(R.drawable.ic_random)
            toolBar.icRight.setOnClickListener {
                searchActivity(true)
            }
        }
    }

    /**
     * Start the adapter for activities list
     */
    private fun recyclerViewInit() {
        adapter = ActivityAdapter(this)
        binding.activitiesRecyclerView.adapter = adapter
    }

    /**
     * Make the api call depending on which activity was selected
     */
    fun searchActivity(random: Boolean = false, type: String = "") {
        val sharedPref = getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
        val participants =
            sharedPref.getInt(Constants.PARTICIPANTS, Constants.PARTICIPANT_DEFAULT_VALUE)

        val dialog = LoadingDialog(this)
        dialog.showAlertDialog()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call: Response<ActivityResponse> = if (participants == 0) {
                    if (random) {
                        ActivitySevice().getRetrofit().create(APIServiceBored::class.java)
                            .getActivityRandom()
                    } else {
                        ActivitySevice().getRetrofit().create(APIServiceBored::class.java)
                            .getActivityByType(type)
                    }
                } else {
                    if (random) {
                        ActivitySevice().getRetrofit().create(APIServiceBored::class.java)
                            .getActivityRandom(participants)
                    } else {
                        ActivitySevice().getRetrofit().create(APIServiceBored::class.java)
                            .getActivityByType(type, participants)
                    }
                }

                val activityResponse: ActivityResponse? = call.body()

                runOnUiThread {
                    if (call.isSuccessful) {
                        activityResponse?.let {

                            if (activityResponse.error.isNullOrEmpty())
                                navigateToDetail(activityResponse, random)
                            else
                                showErrorMessage()

                        } ?: showErrorMessage()
                    } else
                        showErrorMessage()

                    dialog.hideAlertDialog()
                }
            } catch (e: IOException) {
                dialog.hideAlertDialog()
                showErrorMessage(connectionFail = true)
            }
        }
    }

    /**
     * navigate to next view to show detailed info of the activity
     */
    private fun navigateToDetail(activityResponse: ActivityResponse, random: Boolean) {
        println(activityResponse.getPriceStr())
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constants.INTENT_DETAILS, activityResponse)
        intent.putExtra(Constants.INTENT_RANDOM, random)
        startActivity(intent)
    }

    private fun showErrorMessage(connectionFail:Boolean = false) {
        var message = getString(R.string.no_activity_found)
        if (connectionFail)
            message = getString(R.string.connection_error)
        showSnackbar(
            binding.root,
            message,
            isRedAlert = true
        )
    }

}