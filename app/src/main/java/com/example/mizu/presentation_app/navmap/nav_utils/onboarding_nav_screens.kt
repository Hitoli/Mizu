package com.example.mizu.presentation_app.navmap.nav_utils

import com.example.mizu.R

sealed class OnboardingNavScreens(val route:String, val icon:Int, ) {
    data object BodyMeasurementScreen: OnboardingNavScreens(route = "BodyMeasurementScreen", icon = R.drawable.homemizu)
    data object ActivityIntakeScreen: OnboardingNavScreens(route = "ActivityIntakeScreen", icon = R.drawable.calendarmizu)
    data object WaterIntakeResultScreen: OnboardingNavScreens(route = "WaterIntakeResultScreen", icon = R.drawable.calendarmizu)
    data object LoadingScreen: OnboardingNavScreens(route = "LoadingScreen", icon = R.drawable.calendarmizu)
    data object PremiumScreen: OnboardingNavScreens(route = "PremiumScreen", icon = R.drawable.calendarmizu)
    data object ReminderPermissionScreen: OnboardingNavScreens(route = "ReminderPermissionScreen", icon = R.drawable.calendarmizu)
    data object NotificationPermissionScreen: OnboardingNavScreens(route = "NotificationPermissionScreen", icon = R.drawable.calendarmizu)
}