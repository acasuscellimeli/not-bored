package com.app.notbored.service

import android.content.Context
import com.app.notbored.constants.Constants
import com.app.notbored.views.ActivitiesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivitySevice {

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchRandom(activity: ActivitiesActivity) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val participants = sharedPref.getInt(Constants.PARTICIPANTS, Constants.PARTICIPANT_DEFAULT_VALUE)
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIServiceBored::class.java).getActivityRandom(participants)
            val activityResponse : ActivityResponse? = call.body()

            //Corre un pedazo de codigo en el main thread
            activity.runOnUiThread {
                if (call.isSuccessful){
                   println("success")

                }else{
                    //TODO(SHOW_ERROR)
                }
            }

        }
    }

    fun searchByType(activity: ActivitiesActivity,type:String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val participants = sharedPref.getInt(Constants.PARTICIPANTS, Constants.PARTICIPANT_DEFAULT_VALUE)
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIServiceBored::class.java).getActivityByType(type,participants)
            val activityResponse : ActivityResponse? = call.body()
            //Corre un pedazo de codigo en el main thread
            activity.runOnUiThread {
                if (call.isSuccessful){
                    println("success")

                }else{
                    //TODO(SHOW_ERROR)
                }
            }

        }
    }




}