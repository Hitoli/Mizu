package com.example.mizu.features.homescreen.view_model

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.sql.Date
import java.text.DateFormat
import java.util.Calendar

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
    var onTime by mutableStateOf("")
        private set

    fun DismissReward(reward:Boolean){
        rewardDialog = reward
    }

    fun getGreeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        onTime =  when (timeOfDay) {
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..20 -> "Good Evening"
            else -> "Good Night"
        }
    }
    fun fillWaterUpdate(waterUpdate:Int){
        Log.e("WATER PERCENT" ,waterPercent.toString());
        if (waterPercent<=100){

            usedWaterAmount += waterUpdate
            if(usedWaterAmount*100/totalWaterAmount<=100){
                waterPercent = usedWaterAmount*100/totalWaterAmount

            }else{
                waterPercent =100
                rewardDialog = true
            }




        }



        when(waterPercent){
            in 0..30 -> onProgress = "Keep Going, You are doing Great"
            in 30..50 -> onProgress = "You are half way through, keep it up"
            in 80..95 -> onProgress = "You are almost there, keep it going "
            in 95.. 100 -> onProgress = "Amazing, you have achieved your goal"
        }
    }



}