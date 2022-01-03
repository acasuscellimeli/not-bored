package com.app.notbored.views

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.notbored.R
import com.app.notbored.constants.Constants
import com.app.notbored.databinding.ActivityDetailBinding
import com.app.notbored.service.APIServiceBored
import com.app.notbored.service.ActivityResponse
import com.app.notbored.service.ActivitySevice
import com.app.notbored.utils.LoadingDialog
import com.app.notbored.utils.toCapitalize
import com.app.notbored.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureView()
    }

    private fun configureView() {
        val details:ActivityResponse = intent.getSerializableExtra(Constants.INTENT_DETAILS) as ActivityResponse
        val random =intent.getBooleanExtra(Constants.INTENT_RANDOM,false)
        with(binding){
            toolBar.icRight.visibility = View.GONE
            toolBar.toolbarTittle.text = if (random) getString(R.string.txt_random) else details.type.toCapitalize()
            toolBar.icLeft.setBackgroundResource(R.drawable.ic_arrow_left)
            toolBar.icLeft.setOnClickListener {
                onBackPressed()
            }
            txtActivityDescription.text = details.activity
            txtActivityParticipants.text=details.participants.toString()
            txtActivityPrice.text=details.getPriceStr()
            txtActivityType.text=details.type
            val imgResource = resources.getIdentifier("ic_${details.type}", "drawable", packageName)
            imgActivityType.setImageResource(imgResource)
            cardViewType.visibility = if (random) View.VISIBLE else View.GONE
            btnReload.setOnClickListener {
                searchActivity(random,details.type)
            }
        }

    }

    fun searchActivity(random:Boolean = false, type:String = "") {
        val sharedPref = getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
        val participants = sharedPref.getInt(Constants.PARTICIPANTS, Constants.PARTICIPANT_DEFAULT_VALUE)

        val dialog = LoadingDialog(this)
        dialog.showAlertDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<ActivityResponse> = if (participants == 0){
                if (random){
                    ActivitySevice().getRetrofit().create(APIServiceBored::class.java).getActivityRandom()
                }else{
                    ActivitySevice().getRetrofit().create(APIServiceBored::class.java).getActivityByType(type)
                }
            }else{
                if (random){
                    ActivitySevice().getRetrofit().create(APIServiceBored::class.java).getActivityRandom(participants)
                }else{
                    ActivitySevice().getRetrofit().create(APIServiceBored::class.java).getActivityByType(type, participants)
                }
            }

            val activityResponse : ActivityResponse? = call.body()

            runOnUiThread {
                if (call.isSuccessful){
                    activityResponse?.let {

                        if (activityResponse.error.isNullOrEmpty())
                            reLoadView(activityResponse,random)
                        else
                            showErrorMessage()

                    } ?: showErrorMessage()
                }else
                    showErrorMessage()

                dialog.hideAlertDialog()
            }
        }
    }

    private fun showErrorMessage(){
        toast("No activity found with the specified participants")
    }

    private fun reLoadView(activityResponse: ActivityResponse,random: Boolean){
        with(binding){
            toolBar.toolbarTittle.text = if (random) getString(R.string.txt_random) else activityResponse.type.toCapitalize()
            txtActivityDescription.text = activityResponse.activity
            txtActivityParticipants.text = activityResponse.participants.toString()
            txtActivityPrice.text = activityResponse.getPriceStr()
            txtActivityType.text = activityResponse.type
            cardViewType.visibility = if (random) View.VISIBLE else View.GONE
            val imgResource = resources.getIdentifier("ic_${activityResponse.type}", "drawable", packageName)
            imgActivityType.setImageResource(imgResource)
            btnReload.setOnClickListener {
                searchActivity(random, activityResponse.type)
            }
        }
    }
}