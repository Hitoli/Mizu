package com.example.mizu.features.onboarding.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mizu.features.onboarding.source.OnboardingRepository
import com.example.mizu.navigation.navUtils.NavScreens
import com.example.mizu.features.homescreen.utils.UserSettings
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.math.sqrt

class OnboardingViewModel(private val onboardingRepo: OnboardingRepository) : ViewModel() {

    var onNameValue by mutableStateOf("")
        private set
    var onBoardingScreensRoutes by mutableStateOf(NavScreens.SplashNavHostingScreen.route)
        private set
    var onWeightValue by mutableStateOf("")
        private set
    var onWaterAmount by mutableIntStateOf(0)
        private set
    var onHeightValue by mutableStateOf("")
        private set
    var onActiveValue by mutableStateOf<Int?>(null)
        private set
    var onWeightCheck by mutableStateOf(false)
        private set
    var onHeightCheck by mutableStateOf(false)
        private set
    var onWeightError by mutableStateOf("")
        private set
    var onHeightError by mutableStateOf("")
        private set
    var onboardingCompleted by mutableStateOf(false)
        private set
    var _userSettings by mutableStateOf(UserSettings())
        private set
    var checkDigit by mutableStateOf(false)
        private set
    var BSA by mutableStateOf(0)
        private set
    var BWI by mutableStateOf(0)
        private set
    var TWI by mutableStateOf(0.0)
        private set
    var activityLevel by mutableStateOf<Int?>(null)
        private set
    var onActivityLevelCheck by mutableStateOf(false)
        private set
    var onActivityLevelError by mutableStateOf("")
        private set

    var onNotificationPermissionDenied by mutableStateOf<Boolean?>(null)
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

    fun updatePermissionNotification(permission:Boolean){
        onNotificationPermissionDenied = permission
    }

    fun checkBodyMeasurementsFields() {
        onWeightCheck = onWeightValue.isBlank()
        onHeightCheck = onHeightValue.isBlank()
        if (onWeightCheck) {
            onWeightError = "Please Enter the Weight"
        }
        if (onHeightCheck) {
            onHeightError = "Please Enter the Height"
        }
    }

    fun updateOnboardingWaterAmount(value: Int) {
        onWaterAmount = value
    }

    fun onWeightChange(getWeightValue: String) {
        onWeightValue = getWeightValue.filter { it.isDigit() }
    }

    fun onHeightChange(getHeightValue: String) {
        onHeightValue = getHeightValue.filter { it.isDigit() }
    }

    fun onBoardingActiveScreen(getActiveValue: Int) {
        onActiveValue = getActiveValue
        activityLevel = when (onActiveValue) {
            0 -> {
                50
            }

            1 -> {
                35
            }

            else -> {
                20
            }
        }
    }

    fun checkActivtyLevels() {
        onActivityLevelCheck = onActiveValue == null
        onActivityLevelError = if (onActiveValue == null) "Please Add your Activity Levels" else ""
    }

    fun calculateWaterIntake() {
        // Calculate BSA using the Mosteller formula
        BSA = sqrt(onHeightValue.toInt().times(onWeightValue.toDouble() / 3600)).toInt()

        // Calculate BWI using a base factor of 35 ml per kg of body weight
        BWI = onWeightValue.toInt().times(33)

        // Adjust BWI based on BSA
        val BWI_adjusted = BWI * BSA

        // Calculate TWI by adjusting BWI for activity level
        TWI = BWI_adjusted + (BWI_adjusted.times(activityLevel ?: 0) / 100).toDouble()
        Log.d("TWI Onboarding", TWI.toString())
        // Adjust TWI to reasonable limits
        TWI = when {
            TWI > 3700 -> 3700.0
            TWI > 3000 && TWI < 3500 -> 3500.0
            TWI > 2500 && TWI < 3000 -> 3000.0
            TWI > 2000 && TWI < 2500 -> 2500.0
            TWI < 2000 -> 2000.0
            else -> "%.2f".format(TWI).toDouble()
        }

        onWaterAmount = TWI.toInt()
        viewModelScope.launch {
            calculateWaterAmount()
        }
        Log.d("onWaterAmount Onboarding", onWaterAmount.toString())
        TWI /= 1000
        Log.d("TWI Onboarding", "%.2f".format(TWI).toDouble().toString())
    }

    // Update the water Amount in database
    private suspend fun calculateWaterAmount() {
        val date = LocalDate.now()
        onboardingRepo.updateWaterAmount(
            onUsedWater = 0,
            onTotalWaterAmount = onWaterAmount,
            onWaterDay = date.toString()
        )
        println("Date onWaterDay ${date.toString()}")

    }


    fun updateUserSettings(onBoardingCompleted:Boolean) {

        viewModelScope.launch {
            onboardingRepo.updateUserSettingsStore(
                userWeight = onWeightValue.toInt(),
                userWaterIntake = onWaterAmount,
                userName = onNameValue,
                userHeight = onHeightValue.toInt(),
                onBoardingCompleted = onBoardingCompleted
            )

        }
        println("streakScore Onboarding UpdateUserSettings ")

    }


    private suspend fun getUserSettings() {
        onboardingRepo.getUserSettingsStore().collect {
            _userSettings = it
            onNameValue = it.userName
            if (it.userWeight != 0 && it.userHeight != 0) {
                onWeightValue = it.userWeight.toString()
                onHeightValue = it.userHeight.toString()
            } else {
                onWeightValue = ""
                onHeightValue = ""
            }

            TWI = it.userWaterIntake.toDouble()

            onBoardingScreensRoutes = if (!_userSettings.registrationCompleted) {
                NavScreens.OnboardingNavHostingScreen.route
            } else {
                NavScreens.AuthNavHostingScreen.route

            }
            // Add check for Auth screens and Bottom Screens
            println("streakScore Onboarding getUserSettings ${it}")
        }

    }


}