package com.example.mizu.features.onboarding.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.mizu.utils.Utils.Companion.capitalizeFirst

class OnboardingViewModel(): ViewModel(){


    var onNameValue by mutableStateOf("")
        private set

    var onWeightValue by mutableStateOf("")
        private  set

    var onHeightValue by mutableStateOf("")
        private  set

    var onActiveValue by mutableIntStateOf(Int.MIN_VALUE)
        private set

    var check by mutableStateOf(false)
        private set

    var checkDigit by mutableStateOf(false)
        private set

    var BSA by mutableStateOf(0)
        private set
    var BWI by mutableStateOf(0)
        private set
    var TWI by mutableStateOf(0)
        private set
    var activityLevel by mutableStateOf(0)
        private set
    fun checkFields(value:String){
        check = value.isBlank()
        check= value.isDigitsOnly()
        Log.d("check Onboarding",check.toString())

    }
    fun checkDigitFields(value:String){

        checkDigit = value.isNullOrBlank()
        Log.d("check Onboarding",checkDigit.toString())
    }
    fun onBoardingNameScreen(getNameValue:String){
        onNameValue = getNameValue.capitalizeFirst().filter { it.isLetter() }
        Log.d("onNameValue Onboarding",onNameValue)
    }
    fun onBoardingWeightScreen(getWeightValue:String){
            onWeightValue = getWeightValue.filter { it.isDigit() }

        Log.d("onWeightValue Onboarding",onWeightValue.toString())
    }
    fun onBoardingHeightScreen(getHeightValue:String){
        onHeightValue = getHeightValue.filter { it.isDigit() }
        Log.d("getHeightValue Onboarding",onHeightValue.toString())
    }
    fun onBoardingActiveScreen(getActiveValue:Int){
        onActiveValue = getActiveValue
        activityLevel = if (onActiveValue==0){
            50
        }else if(onActiveValue==1){
            35
        }else{
            20
        }
        Log.d("getActiveValue Onboarding",activityLevel.toString())
        Log.d("getActiveValue Onboarding",getActiveValue.toString())
    }

    fun calculateWaterIntake(){
        BSA = Math.sqrt(onHeightValue.toDouble()*onWeightValue.toDouble()/3600).toInt()
        BWI = onWeightValue.toInt()*34*BSA
        TWI = BWI+(BWI*activityLevel/100)

        Log.d("TWI Onboarding",TWI.toString())
    }

}