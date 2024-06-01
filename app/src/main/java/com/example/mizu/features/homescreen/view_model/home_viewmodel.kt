package com.example.mizu.features.homescreen.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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

    var totalWaterAmount by mutableIntStateOf(0)
        private set

    var rewardDialog by mutableStateOf(false)
        private set
    var canAddWater by mutableStateOf(true)
        private set

    var streakDays =  mutableStateListOf<Int>()
        private set
    var waterPercent by mutableIntStateOf(0)
        private set
    var _streak by mutableStateOf(StreakClass())
        private set
    var _waterAmount by mutableStateOf(WaterAmount())
        private set
    var perks = mutableStateListOf<Int>()
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

    // Gets water amount, Gets streak score, Gets compliments for filling water,
    // Updates the water amount at end of the day

    init {

        viewModelScope.launch {
            getWaterAmount()
        }

        println("streakScore Onboarding totalWaterAmount ${totalWaterAmount}")
        viewModelScope.launch {
            getStreakScore()

        }

        waterStreak()
//        updateWaterAmountOnDayEnd()


    }

    // updates the total water amount from onboarding to Homescreen
    fun updateTotalWaterAmount(totalWater:Int){
        totalWaterAmount = totalWater
    }

    // Gets greetings according to the time of day
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

    // Updates the perks in perk sheet
    fun updatePerks(){
        println("Streak Score ${streakScore}")
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


    // Updates the water amount and streak scores
    fun fillWaterUpdate(waterUpdate: Int) {

        val calendar = Calendar.getInstance()
        val currentTime = LocalTime.now()
        val date: LocalDate = LocalDate.now()
        println("currentTime ${date}")


        if (currentTime.minute != waterTime) {
            canAddWater = true
        } else {
            canAddWater = false
        }
        println("streakScore Onboarding date.dayOfMonth ${calendar.get(Calendar.DAY_OF_MONTH)}")

        Log.e("WATER PERCENT", waterPercent.toString());
        if (waterPercent < 100 && canAddWater) {
            waterTime = currentTime.minute

            usedWaterAmount += waterUpdate
            if (usedWaterAmount * 100 / totalWaterAmount <= 100) {
                waterPercent = usedWaterAmount * 100 / totalWaterAmount

            } else {
                waterPercent = 100
                rewardDialog = true
                if (date.toString() != streakDay) {
                    streakScore++
                    streakDays.add(calendar.get(Calendar.DAY_OF_MONTH))
                    updatePerks()
                    viewModelScope.launch {
                        calculateStreakScore()
                    }
                }
                streakDay = date.toString()
            }

            viewModelScope.launch {
                calculateWaterAmount()
            }
            println("streakScore Onboarding streakScore ${streakScore}")
            println("streakScore Onboarding waterTime ${waterTime}")
            println("streakScore Onboarding waterTime ${streakDays}")
            println("streakScore Onboarding currentTime hour ${currentTime.hour}")
            println("streakScore Onboarding streakDay ${streakDay}")
            println("streakScore Onboarding waterAmount ${usedWaterAmount}")
            println("streakScore Onboarding rewardDialog ${rewardDialog}")
            println("streakScore Onboarding streakDay ${streakDay}")
        }
        waterStreak()

    }

    // Update the water Amount in database
    private suspend fun calculateWaterAmount() {
        val date = LocalDate.now()
        onboardingRepo.updateWaterAmount(
            onUsedWater = usedWaterAmount,
            onTotalWaterAmount = totalWaterAmount,
            onWaterDay = date.toString()
        )
        println("Date onWaterDay ${date.toString()}")

    }

    // Update the streak Scores in database
    private suspend fun calculateStreakScore() {
        onboardingRepo.updateStreak(
            streak = streakScore,
            streakDay = streakDay,
            streakDays = streakDays,
            waterTime = waterTime.toString(),
            perks =perks
        )
        println("streakScore Onboarding calculateStreakScore ")

    }

    // Gets the water amount filled in the glacier from the database
    // Tries to compare todays date versus previous water date. If the dates are different, the water amount becomes zero
    // It also calculates and updates the water percentage
    private suspend fun getWaterAmount() {
        val date = LocalDate.now()
        onboardingRepo.getWaterAmount().collect {
            println("Get Water Amount Home Viewmodel Function ${it}")
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
            if(date.toString()!=it.onWaterDay){
                usedWaterAmount = 0
                waterPercent =0
                calculateWaterAmount()
            }
        }
    }


    // Gets the streak score and updates perks in perk sheet

    private suspend fun getStreakScore() {
        onboardingRepo.getStreak().collect { it ->
            _streak =it
            streakScore = it.streak
            if(it.perks.isNotEmpty()){
                perks.clear()
                println("perks ${it.perks}")

                it.perks.forEach{
                    if(!perks.contains(it)){
                        perks.add(it)
                    }
                }
               /*perks.addAll(it.perks)*/
            }
            if (it.waterTime != "") {
                waterTime = it.waterTime.toInt()
            }
            streakDay = it.streakDay

            println("getStreakScore ${it}")
        }
    }

    // Gives compliments according to percentage of water filled in the glacier
    fun waterStreak() {
        when (waterPercent) {
            in 0..30 -> onProgress = "Keep Going, You are doing Great"
            in 30..50 -> onProgress = "You are half way through, keep it up"
            in 80..95 -> onProgress = "You are almost there, keep it going "
            in 95..100 -> onProgress = "Amazing"
        }
    }



}