package com.example.mizu.features.homescreen.view_model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel():ViewModel() {

    var usedWaterAmount by mutableIntStateOf(0)
        private set


    var totalWaterAmount by mutableIntStateOf(2400)
        private set

    var rewardDialog by mutableStateOf(false)
        private set

    var waterPercent by mutableIntStateOf(0)
        private set

    var onProgress by mutableStateOf("")
        private set

    fun DismissReward(reward:Boolean){
        rewardDialog = reward
    }

    fun fillWaterUpdate(waterUpdate:Int){
        if(usedWaterAmount>=totalWaterAmount){
            rewardDialog = true

        }else{
            usedWaterAmount += waterUpdate
        }
        waterPercent = usedWaterAmount*100/totalWaterAmount

        when(waterPercent){
            30 -> onProgress = "Keep Going, You are doing Great"
            50 -> onProgress = "You are half way through, keep it up"
            80 -> onProgress = "You are almost there, keep it going "
            100 -> onProgress = "Amazing, you have achieved your goal"
        }
    }



}