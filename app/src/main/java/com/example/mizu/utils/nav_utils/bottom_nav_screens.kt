package com.example.mizu.utils.nav_utils

import com.example.mizu.R

sealed class BottomNavScreens(val route:String, val icon:Int, ) {
    data object HomeScreen: BottomNavScreens(route = "Home", icon = R.drawable.homemizu)
    data object CalendarScreen: BottomNavScreens(route = "Track", icon = R.drawable.calendarmizu)
    data object ProfileScreen: BottomNavScreens(route = "Profile", icon = R.drawable.ic_profile_bottom_bar)

}