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

    init {
        viewModelScope.launch {
            getStreakScore()
        }

        getWaterGoals()

    }

    fun updateWaterGoals(index:Int){
        _onWaterGoals[index] = _onWaterGoals[index].copy(onSelected = !_onWaterGoals[index].onSelected)
        //        _onWaterGoals.get(index).onSelected = !_onWaterGoals.get(index).onSelected
    }

    fun getWaterGoals(){
        _onWaterGoals.clear()

        _onWaterGoals.addAll(listOf(
            WaterGoals(goal = "Keep a bottle by your desk",onSelected = false),
            WaterGoals(goal = "Keep a bottle by your desk",onSelected = false),
            WaterGoals(goal = "Keep a bottle by your desk",onSelected = false)
        ))
    }

    private suspend fun getStreakScore() {
        onboardingRepo.getStreak().collect {
            _streak = it
            onStreakDays = it.streak
            streaKDays.addAll(it.streakDays)
            getCalendarValues()
            println(" calendarScreen streaKDays ${it.streakDays}")
            println(" calendarScreen streaKDays ${streaKDays}")
//            println("streakScore calendarScreen onStreakDays ${streaKDays[0]}")
        }


    }


    fun getCalendarValues(){

        // Getting Month value for the calendar
        val calendar = Calendar.getInstance()
        val monthIndex = calendar.get(Calendar.MONTH)
        val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        onMonth =  months[monthIndex]
        onDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        for(i in 0 until 5){
            val rowList = mutableListOf<Color>()
            for(j in 0 until 7){
                val count =i*7+j

                if(count<onDays){
                    println("streakScore calendarScreen streakDays ${streaKDays}")
//                    if(k<streaKDays.size){
                        println("streakScore calendarScreen count ${count}")
                        if(streaKDays.contains(count)){

//                        println("streakScore calendarScreen streaKDays ${streaKDays[count]}")
//                        if(count==streaKDays[count] ){
                            rowList.add(waterColor)
//                        }else{
//                            rowList.add(minorColor)
//                        }
                        }else{
                            rowList.add(minorColor)
                        }

//                    }else{
//                        rowList.add(minorColor)
//                    }

                }else{
                    rowList.add(minorColor)
                }

            }
            calendarList.add(rowList)
        }







    }

}