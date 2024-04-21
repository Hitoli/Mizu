package com.example.mizu.utils.home_screen_utils

import kotlinx.serialization.Serializable

@Serializable
data class StreakClass(
    var streak:Int=0,
    val streakDay:String="Sunday",
    val streakBroken:Int=0,
    val waterTime:String=""
)

@Serializable
data class StreakMonthClass(
    var streakMonth:Int=0,
    var streakDay:Int=0,
    var streakYear:Int=0,
)

