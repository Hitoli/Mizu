package com.example.mizu.features.onboarding.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mizu.model.OnboardingRepository
import com.example.mizu.utils.home_screen_utils.StreakClass
import com.example.mizu.utils.Utils.Companion.capitalizeFirst
import com.example.mizu.utils.home_screen_utils.StreakMonthClass
import com.example.mizu.utils.home_screen_utils.UserSettings
import kotlinx.coroutines.launch

class OnboardingViewModel(private val onboardingRepo:OnboardingRepository): ViewModel(){

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
    var _streak by mutableStateOf(StreakClass())
        private set
    var _userSettings by mutableStateOf(UserSettings())
        private set
    var _streakMonth by mutableStateOf(StreakMonthClass())
        private set

    var streakScore by mutableIntStateOf(0)
        private set
    var streakDay by mutableStateOf("")
        private set
    var streakBroken by mutableIntStateOf(0)
        private set
    var checkDigit by mutableStateOf(false)
        private set

    var streakMonthDay by mutableIntStateOf(0)
        private set
    var streakMonth by mutableIntStateOf(0)
        private set

    var streakYear by mutableIntStateOf(0)
        private set

    var BSA by mutableStateOf(0)
        private set
    var BWI by mutableStateOf(0)
        private set
    var TWI by mutableStateOf(0.0)
        private set
    var activityLevel by mutableStateOf(0)
        private set


    init {
    viewModelScope.launch {
//        calculateStreakScore()
//        getStreakScore()
//        calculateStreakMonthScore()
//        getMonthStreak()
        getUserSettings()
    }

    }
    fun checkFields(value:String){
        check = value.isBlank()
        check= value.isDigitsOnly()
        Log.d("check Onboarding",check.toString())

    }
    fun checkDigitFields(value:String){
        checkDigit = value.isNullOrBlank()
        Log.d("checkDigit Onboarding",checkDigit.toString())
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
        TWI = (BWI+(BWI*activityLevel/100)).toDouble()
        if (TWI>4){
            TWI = 3700.0
        }else if(TWI<2){
            TWI  =2700.0
        }
        TWI/=1000

        viewModelScope.launch{
            updateUserSettings()
        }

        Log.d("TWI Onboarding",TWI.toString())
    }

     private suspend fun calculateStreakScore(){
         onboardingRepo.updateStreak(streak = streakScore, streakDay =streakDay, streakBroken = streakBroken )
         println("streakScore Onboarding calculateStreakScore ")

    }
    private suspend fun calculateStreakMonthScore(){
        onboardingRepo.updateStreakMonth(streakYear =streakYear , streakMonth = streakMonth, streakDay = streakMonthDay)
        println("streakScore Onboarding calculateStreakMonthScore ")

    }
    private suspend fun updateUserSettings(){
        onboardingRepo.updateUserSettingsStore(userWeight = onWeightValue.toInt(), userWaterIntake =TWI.toInt() , userName =onNameValue , userHeight = onHeightValue.toInt())
        println("streakScore Onboarding UpdateUserSettings ")

    }

    private suspend fun getStreakScore() {
        onboardingRepo.getStreak().collect {
            _streak = it
            println("streakScore Onboarding getStreakScore ${it}")
        }

    }
    private suspend fun getMonthStreak() {
        onboardingRepo.getStreakMonth().collect {
            _streakMonth = it
            println("streakScore Onboarding getMonthStreak ${it}")
        }

    }
    private suspend fun getUserSettings() {
        onboardingRepo.getUserSettingsStore().collect {
            _userSettings = it
            println("streakScore Onboarding getUserSettings ${it}")
        }

    }


}