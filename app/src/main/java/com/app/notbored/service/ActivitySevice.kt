package com.app.notbored.service

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.app.notbored.constants.Constants
import com.app.notbored.views.ActivitiesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class ActivitySevice {

    fun getRetrofit() : Retrofit {

//        val okHttpClient = OkHttpClient.Builder()
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .writeTimeout(20, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
//            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}