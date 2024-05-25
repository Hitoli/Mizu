package com.example.mizu.utils.nav_utils

sealed class NavScreens(var route:String){
    object  BottomNavHostingScreen: NavScreens(route = "BottomNavHostingScreen")
    object  OnboardingNavHostingScreen: NavScreens(route = "OnboardingNavHostingScreen")
    object  SplashNavHostingScreen: NavScreens(route = "SplashNavHostingScreen")
    object ProfileNavHostingScreen: NavScreens(route = "ProfileNavHostingScreen")
}