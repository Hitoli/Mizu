package com.example.mizu.features.homescreen.utils

import kotlinx.serialization.Serializable

@Serializable
data class WaterAmount(
    var onUsedWater:Int=0,
    var onTotalWater:Int=0,
    var onWaterDay:String = ""
)
