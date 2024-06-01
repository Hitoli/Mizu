package com.example.mizu.features.calendarscreen.view_model

import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mizu.model.OnboardingRepository
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.utils.calendar_utils.WaterGoals
import com.example.mizu.utils.home_screen_utils.StreakClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class CalendarViewModel(private val onboardingRepo: OnboardingRepository):ViewModel() {

    var onMonth by mutableStateOf("")
        private set

    private var _onWaterGoals = mutableStateListOf<WaterGoals>()
        var onWaterGoals:List<WaterGoals> = _onWaterGoals

    var onDays by mutableIntStateOf(0)
        private set

    var streaKDays  = mutableStateListOf<Int>()
        private set
    var _streak by mutableStateOf(StreakClass())
        private set
    var onStreakDays by mutableIntStateOf(0)
        private set

    var calendarList:MutableList<List<Color>> = mutableListOf()
        private set

    var avgWaterIntake by mutableStateOf("")
        private set
    var bestStreak by mutableStateOf("")
        private set
    var weight by mutableStateOf("")
        private set
    var height by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            getStreakScore()
        }
        viewModelScope.launch {
            getUserSettings()
        }

//        getWaterGoals()

    }

    fun updateWaterGoals(index:Int){
        _onWaterGoals[index] = _onWaterGoals[index].copy(onSelected = !_onWaterGoals[index].onSelected)
        //        _onWaterGoals.get(index).onSelected = !_onWaterGoals.get(index).onSelected
    }

//    fun getWaterGoals(){
//        _onWaterGoals.clear()
//
//        _onWaterGoals.addAll(listOf(
//            WaterGoals(goal = "Pre-fill water bottle in the morning",onSelected = false),
//            WaterGoals(goal = "Keep a bottle by your desk",onSelected = false),
//            WaterGoals(goal = "Drink your first cup of water",onSelected = false)
//        ))
//    }

    private suspend fun getStreakScore() {
        onboardingRepo.getStreak().collect {
            _streak = it
            onStreakDays = it.streak
            streaKDays.addAll(it.streakDays)
            getCalendarValues()
            println(" calendarScreen streaKDays ${it.streakDays}")
            println(" calendarScreen streaKDays ${streaKDays}")
        }
        calculateUserValues()
    }
    private suspend fun calculateUserValues(){
        if (bestStreak.toInt()<onStreakDays){
            bestStreak = onStreakDays.toString()
        }
        onboardingRepo.updateUserValues(avgIntake ="1000",bestStreak = bestStreak )
    }

    private suspend fun getUserSettings(){
        onboardingRepo.getUserValues().collect{
            avgWaterIntake = it.avgIntake
            bestStreak = it.bestStreak
        }
        onboardingRepo.getUserSettingsStore().collect{

            height = it.userHeight.toString()
            weight = it.userWeight.toString()


        }
    }


    fun getCalendarValues(){

        // Getting Month value for the calendar
        val calendar = Calendar.getInstance()
        val monthIndex = calendar.get(Calendar.MONTH)
        val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        onMonth =  months[monthIndex]
        onDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        calendarList.clear()
        for(i in 0 until 5){
            val rowList = mutableListOf<Color>()
            for(j in 0 until 7){
                val count =i*7+j
                if(count<onDays){
                    println("streakScore calendarScreen streakDays ${streaKDays}")
                        if(streaKDays.size!=0 && streaKDays.contains(count)){
                            println("streakScore calendarScreen count ${count}")
                            rowList.add(count-1, waterColor)
                        }else{
                            rowList.add(minorColor)
                        }

                }
            }
            calendarList.add(rowList)
        }







    }

}