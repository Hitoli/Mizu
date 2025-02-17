package com.example.mizu.presentation_app.navmap.nav_utils

sealed class NavScreens(var route:String){
    data object  BottomNavHostingScreen: NavScreens(route = "BottomNavHostingScreen")
    data object  OnboardingNavHostingScreen: NavScreens(route = "OnboardingNavHostingScreen")
    data object  SplashNavHostingScreen: NavScreens(route = "SplashNavHostingScreen")
    data object AuthNavHostingScreen: NavScreens(route = "AuthNavHostingScreen")
}