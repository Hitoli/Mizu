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
import co.yml.charts.common.extensions.isNotNull
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
    var bestStreak by mutableStateOf(0)
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
        viewModelScope.launch {
            getUserValues()
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
            println(" calendarScreen streak ${it.streak}")
            calculateUserValues()
        }

    }
    private suspend fun calculateUserValues(){
        if(onStreakDays == null || onStreakDays == 0){
            bestStreak =0
        }else{
            if (bestStreak<=onStreakDays){
                bestStreak = onStreakDays
            }
        }

        Log.e("User Settings:==> BestStreak ", bestStreak.toString())
        Log.e("User Settings:==> onStreakDays ", onStreakDays.toString())
        onboardingRepo.updateUserValues(avgIntake ="1000",bestStreak = bestStreak.toString() )
    }

    private suspend fun getUserValues(){
        onboardingRepo.getUserValues().collect{
            avgWaterIntake = it.avgIntake
            if(it.bestStreak.isEmpty()){
                bestStreak = 0
            }else{
                bestStreak = it.bestStreak.toInt()
            }

            Log.e("User Settings:==> BestStreak ", bestStreak.toString())

        }
    }

    private suspend fun getUserSettings(){
        onboardingRepo.getUserSettingsStore().collect{
            height = it.userHeight.toString()
            weight = it.userWeight.toString()
//            Log.e("User Settings:==> Height  ", height)
//            Log.e("User Settings:==> Weight ", weight)
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
                        if(streaKDays.isNotEmpty() && streaKDays.contains(count)){
                            println("streakScore calendarScreen count ${count}")
                            rowList.add(waterColor)
                        }else{
                            rowList.add(minorColor)
                        }

                }
            }
            calendarList.add(rowList)
        }







    }

}