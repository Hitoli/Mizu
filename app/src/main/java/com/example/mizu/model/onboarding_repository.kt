package com.example.mizu.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.mizu.utils.SerializerStreak
import com.example.mizu.utils.SerializerStreakMonth
import com.example.mizu.utils.SerializerUserSetting
import com.example.mizu.utils.home_screen_utils.StreakClass
import com.example.mizu.utils.home_screen_utils.StreakMonthClass
import com.example.mizu.utils.home_screen_utils.UserSettings
import kotlinx.coroutines.flow.Flow


class OnboardingRepository(context: Context) {
    private val Context.streakStore: DataStore<StreakClass> by dataStore(
        fileName = "streak_store.json",
        serializer = SerializerStreak,
    )
    private val Context.streakStoreMonth: DataStore<StreakMonthClass> by dataStore(
        fileName = "streak_store_month.json",
        serializer = SerializerStreakMonth,
    )
    private val Context.userSettingsStore: DataStore<UserSettings> by dataStore(
        fileName = "user_settings_store.json",
        serializer = SerializerUserSetting,
    )

    var streakScore:DataStore<StreakClass> = context.streakStore
     suspend fun updateStreak(streak:Int,streakBroken:Int,streakDay:String,waterTime:String){
        streakScore.updateData {
            it.copy(
                streak =streak,
                streakBroken = streakBroken,
                streakDay = streakDay,
                waterTime = waterTime
            )
        }
         println("streakScore Onboarding updateStreak ${streak}")


     }
     fun getStreak():Flow<StreakClass> = streakScore.data


    var streakMonthScore:DataStore<StreakMonthClass> = context.streakStoreMonth
    suspend fun updateStreakMonth(streakDay:Int,streakMonth:Int,streakYear:Int){
        streakMonthScore.updateData {
            it.copy(
                streakDay = streakDay,
                streakMonth = streakMonth,
                streakYear = streakYear
            )
        }
        println("streakScore Onboarding updateStreakMonth ${streakDay}")


    }
    fun getStreakMonth():Flow<StreakMonthClass> = streakMonthScore.data

    var userSettingsStore:DataStore<UserSettings> = context.userSettingsStore
    suspend fun updateUserSettingsStore(userHeight:Int,userWaterIntake:Int,userName:String,userWeight:Int,onBoardingCompleted:Boolean){
        userSettingsStore.updateData {
           it.copy(
               userHeight = userHeight,
               userName = userName,
               userWaterIntake = userWaterIntake,
               userWeight = userWeight,
               registrationCompleted = onBoardingCompleted
           )
        }
        println("streakScore Onboarding updateUserSettingsStore ${userWaterIntake}")


    }
    fun getUserSettingsStore():Flow<UserSettings> = userSettingsStore.data

}