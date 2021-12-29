package com.app.notbored.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface APIServiceBored {
    @GET("activity/")
    suspend fun getActivityRandom(@Path("participants") participants:Int) : Response<ActivityResponse>

    @GET
    suspend fun getActivityByType(@Query("type") type:String , @Query("participants") participants:Int):Response<ActivityResponse>
}