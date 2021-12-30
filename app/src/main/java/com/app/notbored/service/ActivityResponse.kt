package com.app.notbored.service

import java.io.Serializable

data class ActivityResponse(
    var activity:String,
    var type:String,
    var participants:Int,
    var price:Double,
    var link:String,
    var key:String,
    var accessibility:Double,
    var error:String?,
) : Serializable{

    fun getPriceStr() : String{
        return when {
            price == 0.toDouble() -> "Free"
            price <= 0.3 -> "Low"
            price <= 0.6 -> "Medium"
            else -> "High"
        }
    }
}
