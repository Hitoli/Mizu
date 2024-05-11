package com.example.mizu.features.homescreen.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mizu.R
import com.example.mizu.model.OnboardingRepository
import com.example.mizu.utils.home_screen_utils.StreakClass
import com.example.mizu.utils.home_screen_utils.StreakMonthClass
import com.example.mizu.utils.home_screen_utils.WaterAmount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Calendar


class HomeViewModel(private val onboardingRepo: OnboardingRepository) : ViewModel() {

    var usedWaterAmount by mutableIntStateOf(0)
        private set

    var streakMonthDay by mutableIntStateOf(0)
        private set
    var streakMonth by mutableIntStateOf(0)
        private set

    var streakYear by mutableIntStateOf(0)
        private set

    var totalWaterAmount by mutableIntStateOf(0)
        private set

    var rewardDialog by mutableStateOf(false)
        private set
    var canAddWater by mutableStateOf(true)
        private set

    var streakBroken by mutableIntStateOf(0)
        private set
    var waterPercent by mutableIntStateOf(0)
        private set
    var _streak by mutableStateOf(StreakClass())
        private set

    var _streakMonth by mutableStateOf(StreakMonthClass())
        private set
    var _waterAmount by mutableStateOf(WaterAmount())
        private set
    var perks = mutableListOf<Int>()
        private set
    var streakScore by mutableIntStateOf(0)
        private set
    var streakDay by mutableStateOf("")
        private set
    var waterTime by mutableStateOf(24)
        private set
    var onProgress by mutableStateOf("")
        private set
    var onTime by mutableStateOf("")
        private set

    fun DismissReward(reward: Boolean) {
        rewardDialog = reward
    }

    init {

        viewModelScope.launch {
            getWaterAmount()
        }

        println("streakScore Onboarding totalWaterAmount ${totalWaterAmount}")
        viewModelScope.launch {
            getStreakScore()

        }
        viewModelScope.launch {
            getMonthStreak()
        }
        waterStreak()


    }
    fun updateTotalWaterAmount(totalWater:Int){
        totalWaterAmount = totalWater
    }

    fun getGreeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        onTime = when (timeOfDay) {
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..20 -> "Good Evening"
            else -> "Good Night"
        }
    }

    fun updatePerks(){
        when {
            streakScore>=1 -> {
                perks.add(R.drawable.day1)
            }
            streakScore>=7 -> {
                perks.add(R.drawable.day3)
            }
            streakScore>=14 -> {
                perks.add(R.drawable.day4)
            }
            streakScore>=30 -> {
                perks.add(R.drawable.day2)
            }
        }
    }

    private fun observeTime() {
        viewModelScope.launch(Dispatchers.IO) {
            val timeFlow = createTimeFlow()

            timeFlow.collect { currentTime ->
                // Check if the minute of the current time matches a specific water time (e.g., 30 minutes)
                if (currentTime.minute != waterTime) {
                    canAddWater = true
                } else {
                    canAddWater = false
                }
            }
        }
    }

    fun fillWaterUpdate(waterUpdate: Int) {

        val calendar = Calendar.getInstance()
        val currentTime = LocalTime.now()
        val timeOfDay = calendar.get(Calendar.DAY_OF_WEEK)
        val timeOfMonth = calendar.get(Calendar.MONTH)
        val timeOfYear = calendar.get(Calendar.YEAR)
        val date: LocalDate = LocalDate.of(timeOfYear, timeOfMonth + 1, timeOfDay)

        if (currentTime.minute != waterTime) {
            canAddWater = true
        } else {
            canAddWater = false
        }


        Log.e("WATER PERCENT", waterPercent.toString());
        if (waterPercent <= 100 && canAddWater) {
            waterTime = currentTime.minute

            usedWaterAmount += waterUpdate
            if (usedWaterAmount * 100 / totalWaterAmount <= 100) {
                waterPercent = usedWaterAmount * 100 / totalWaterAmount

            } else {
                waterPercent = 100
                rewardDialog = true
                if (date.toString() != streakDay) {
                    streakScore++
                }
                streakDay = date.toString()
            }
            updatePerks()

            viewModelScope.launch {
                calculateStreakScore()
                calculateStreakMonthScore()
                calculateWaterAmount()
            }
            println("streakScore Onboarding streakScore ${streakScore}")
            println("streakScore Onboarding waterTime ${waterTime}")
            println("streakScore Onboarding currentTime hour ${currentTime.hour}")
            println("streakScore Onboarding streakDay ${streakDay}")
            println("streakScore Onboarding waterAmount ${usedWaterAmount}")
            println("streakScore Onboarding rewardDialog ${rewardDialog}")
            println("streakScore Onboarding streakDay ${streakDay}")
        }

        waterStreak()

    }

    private suspend fun calculateWaterAmount() {
        onboardingRepo.updateWaterAmount(
            onUsedWater = usedWaterAmount,
            onTotalWaterAmount = totalWaterAmount
        )
        println("streakScore Onboarding calculateWaterAmount ")

    }

    private suspend fun calculateStreakScore() {
        onboardingRepo.updateStreak(
            streak = streakScore,
            streakDay = streakDay.toString(),
            streakBroken = streakBroken,
            waterTime = waterTime.toString(),
            perks =perks
        )
        println("streakScore Onboarding calculateStreakScore ")

    }

    private suspend fun calculateStreakMonthScore() {
        onboardingRepo.updateStreakMonth(
            streakYear = streakYear,
            streakMonth = streakMonth,
            streakDay = streakMonthDay
        )
        println("streakScore Onboarding calculateStreakMonthScore ")

    }

    private suspend fun getWaterAmount() {
        onboardingRepo.getWaterAmount().collect {
            _waterAmount = it
            usedWaterAmount = it.onUsedWater
            totalWaterAmount = it.onTotalWater


            if (it.onUsedWater > 0) {
                if (it.onUsedWater * 100 / it.onTotalWater <= 100) {
                    waterPercent = it.onUsedWater * 100 / it.onTotalWater

                } else {
                    waterPercent = 100
                }

            }
            println("streakScore Onboarding waterPercent ${waterPercent}")

//            else{
//                viewModelScope.launch {
//                    calculateWaterAmount()
//                }
//            }

        }

        println("streakScore Onboarding getWaterAmount ${totalWaterAmount}")
        println("streakScore Onboarding usedWaterAmount ${usedWaterAmount}")


    }

    private suspend fun getStreakScore() {
        onboardingRepo.getStreak().collect {
            _streak = it
            if (it.waterTime != "") {
                waterTime = it.waterTime.toInt()
            }
            streakDay = it.streakDay
            if(perks.isNotEmpty()){
                perks.addAll(it.perks)

            }

            println("streakScore Onboarding getStreakScore ${it}")
        }

    }

    private suspend fun getMonthStreak() {
        onboardingRepo.getStreakMonth().collect {
            _streakMonth = it
            println("streakScore Onboarding getMonthStreak ${it}")
        }

    }

    fun waterStreak() {
        when (waterPercent) {
            in 0..30 -> onProgress = "Keep Going, You are doing Great"
            in 30..50 -> onProgress = "You are half way through, keep it up"
            in 80..95 -> onProgress = "You are almost there, keep it going "
            in 95..100 -> onProgress = "Amazing"
        }
    }

    private fun createTimeFlow() = flow {
        while (true) {
            val currentTime = LocalDateTime.now()
            emit(currentTime)
            delay(1000) // Emit the current time every second
            Log.e("Water Time", currentTime.hour.toString())
        }
    }


}