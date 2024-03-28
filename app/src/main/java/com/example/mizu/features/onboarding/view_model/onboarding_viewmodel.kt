package com.example.mizu.features.onboarding.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class OnboardingViewModel(): ViewModel(){


    var onNameValue by mutableStateOf("")
        private set

    var onWeightValue by mutableStateOf("")
        private  set

    var onHeightValue by mutableStateOf("")
        private  set

    var onActiveValue by mutableStateOf(0)
        private set

    fun onBoardingNameScreen(getNameValue:String){
        onNameValue = getNameValue
        Log.d("onNameValue Onboarding",onNameValue)
    }
    fun onBoardingWeightScreen(getWeightValue:String){
        onWeightValue = getWeightValue
        Log.d("onWeightValue Onboarding",onWeightValue.toString())
    }
    fun onBoardingHeightScreen(getHeightValue:String){
        onHeightValue = getHeightValue
        Log.d("getHeightValue Onboarding",onHeightValue.toString())
    }
    fun onBoardingActiveScreen(getActiveValue:Int){
        onActiveValue = getActiveValue
        Log.d("getActiveValue Onboarding",getActiveValue.toString())
    }

}