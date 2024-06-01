package com.example.mizu.utils.home_screen_utils

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    var userName:String="",
    var userHeight:Int=0,
    var userWeight:Int=0,
    var userWaterIntake:Int=0,
    var registrationCompleted:Boolean =false,
)

@Serializable
data class UserValues(
    var avgIntake:String ="",
    var bestStreak:String=""
)
