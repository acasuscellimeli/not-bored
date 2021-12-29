package com.app.notbored.service

import com.google.gson.annotations.SerializedName

data class ActivityResponse(
    var activity : String,
    var type :String,
    var participants:Int,
    var price:Double,
    var link:String,
    var key:String,
    var accessibility:Double
)
