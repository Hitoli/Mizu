package com.example.mizu.utils

import com.example.mizu.R
import java.util.Vector

sealed class BottomNavScreens(val route:String, val icon:Int, ) {
    object HomeScreen:BottomNavScreens(route = "Home", icon = R.drawable.homemizu)
    object CalendarScreen:BottomNavScreens(route = "Track", icon = R.drawable.calendarmizu)

}