package com.example.mizu.features.onboarding.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.mizu.model.storage_utils.SerializerStreak
import com.example.mizu.model.storage_utils.SerializerStreakMonth
import com.example.mizu.model.storage_utils.SerializerUserSetting
import com.example.mizu.model.storage_utils.SerializerWaterAmount
import com.example.mizu.features.homescreen.utils.StreakClass
import com.example.mizu.features.homescreen.utils.StreakMonthClass
import com.example.mizu.features.homescreen.utils.UserSettings
import com.example.mizu.features.homescreen.utils.UserValues
import com.example.mizu.features.homescreen.utils.WaterAmount
import com.example.mizu.model.storage_utils.SerializerUserValues
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
    private val Context.waterAmount: DataStore<WaterAmount> by dataStore(
        fileName = "water_amount.json",
        serializer = SerializerWaterAmount,
    )

    private val Context.userValues:DataStore<UserValues> by dataStore(
        fileName = "user_values.json",
        serializer = SerializerUserValues
    )

    var userValues:DataStore<UserValues> = context.userValues

    suspend fun updateUserValues(avgIntake:String,bestStreak:String){
        userValues.updateData {
            it.copy(
                bestStreak = bestStreak,
                avgIntake = avgIntake
            )
        }
    }
    fun getUserValues():Flow<UserValues> = userValues.data

    var streakScore:DataStore<StreakClass> = context.streakStore
     suspend fun updateStreak(streak:Int,streakDays:List<Int>,streakDay:String,waterTime:String,perks: List<Int>){
        streakScore.updateData {
            it.copy(
                streak =streak,
                streakDays = streakDays,
                streakDay = streakDay,
                waterTime = waterTime,
                perks = perks
            )
        }
         println("streakScore Onboarding updateStreak ${perks}")
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
               registrationCompleted = onBoardingCompleted,

           )
        }
        println("streakScore Onboarding updateUserSettingsStore ${userWaterIntake}")


    }
    fun getUserSettingsStore():Flow<UserSettings> = userSettingsStore.data

    var waterAmount:DataStore<WaterAmount> = context.waterAmount
    suspend fun updateWaterAmount(onUsedWater:Int,onTotalWaterAmount: Int,onWaterDay:String){
        waterAmount.updateData {
            it.copy(
                onUsedWater= onUsedWater,
                onTotalWater = onTotalWaterAmount,
                onWaterDay =onWaterDay
            )
        }
        println("streakScore Onboarding WaterAmount ${onUsedWater}")


    }
    fun removeWaterAmount(){

    }
    fun getWaterAmount():Flow<WaterAmount> = waterAmount.data

}