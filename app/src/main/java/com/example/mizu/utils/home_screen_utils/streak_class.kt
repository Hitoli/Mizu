package com.example.mizu.utils.home_screen_utils

import kotlinx.serialization.Serializable

@Serializable
data class StreakClass(
    var streak:Int=0,
    val streakDay:String="Sunday",
    val streakDays:List<Int> = listOf(0),
    val waterTime:String="",
    val perks:List<Int> = listOf(0)
)

@Serializable
data class StreakMonthClass(
    var streakMonth:Int=0,
    var streakDay:Int=0,
    var streakYear:Int=0,
)

