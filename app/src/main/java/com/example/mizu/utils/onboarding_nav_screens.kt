package com.example.mizu.utils

import com.example.mizu.R

sealed class OnboardingNavScreens(val route:String, val icon:Int, ) {
    object WeightIntakeScreen:OnboardingNavScreens(route = "WeightIntakeScreen", icon = R.drawable.homemizu)
    object HeightIntakeScreen:OnboardingNavScreens(route = "HeightIntakeScreen", icon = R.drawable.calendarmizu)
    object ActivityIntakeScreen:OnboardingNavScreens(route = "ActivityIntakeScreen", icon = R.drawable.calendarmizu)
    object WaterIntakeResultScreen:OnboardingNavScreens(route = "WaterIntakeResultScreen", icon = R.drawable.calendarmizu)
    object LoadingScreen:OnboardingNavScreens(route = "LoadingScreen", icon = R.drawable.calendarmizu)
    object NameScreen:OnboardingNavScreens(route = "NameScreen", icon = R.drawable.calendarmizu)

}