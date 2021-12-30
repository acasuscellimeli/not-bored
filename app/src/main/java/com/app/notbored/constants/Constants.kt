package com.app.notbored.constants

import retrofit2.http.Url

object Constants {
    val ACTIVITIES = listOf("education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork")
    const val PARTICIPANTS = "participants_key"
    const val PARTICIPANT_DEFAULT_VALUE = 1
    const val BASE_URL = "http://www.boredapi.com/api/"
    const val SHARED_PREF = "sharedpref"
    const val INTENT_DETAILS = "activityResponse"
    const val INTENT_RANDOM = "randomActivity"


}