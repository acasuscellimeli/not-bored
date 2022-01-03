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
import com.app.notbored.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

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

    private fun setUpToolBar() {
        with(binding){
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

    private fun recyclerViewInit(){
        adapter = ActivityAdapter(this)
        binding.activitiesRecyclerView.adapter = adapter
    }

    fun searchActivity(random:Boolean = false, type:String = "") {
        val sharedPref = getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
        val participants = sharedPref.getInt(Constants.PARTICIPANTS, Constants.PARTICIPANT_DEFAULT_VALUE)

        val dialog = LoadingDialog(this)
        dialog.showAlertDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<ActivityResponse> = if (random){
                ActivitySevice().getRetrofit().create(APIServiceBored::class.java).getActivityRandom(participants)
            }else{
                ActivitySevice().getRetrofit().create(APIServiceBored::class.java).getActivityByType(type, participants)
            }

            val activityResponse : ActivityResponse? = call.body()

            runOnUiThread {
                if (call.isSuccessful){
                    activityResponse?.let {

                        if (activityResponse.error.isNullOrEmpty())
                            navigateToDetail(activityResponse,random)
                        else
                            showErrorMessage()

                    } ?: showErrorMessage()
                }else
                    showErrorMessage()

                dialog.hideAlertDialog()
            }
        }
    }

    private fun navigateToDetail(activityResponse : ActivityResponse,random:Boolean){
        println(activityResponse.getPriceStr())
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(Constants.INTENT_DETAILS,activityResponse)
        intent.putExtra(Constants.INTENT_RANDOM,random)
        startActivity(intent)
    }

    private fun showErrorMessage(){
        toast("No activity found with the specified participants")
    }

}