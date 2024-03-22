package com.example.mizu.presentation_app.navmap

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.homescreen.presentation.HomeScreen
import com.example.mizu.utils.NavScreens

@Composable
fun NavScreen() {

    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreens.OnboardingNavHostingScreen.route){
       composable(route = NavScreens.BottomNavHostingScreen.route){
           HomeScreen(
               onPad = PaddingValues(40.dp),
               onWaterTrackingResourceAmount =200 ,
               getWaterTrackingResourceAmount = {},
               onTotalWaterTrackingResourceAmount =2000 ,
               getAddWater = { /*TODO*/ },
               onUserName = "Hitesh",
               getReward = {},
               onReward =false
           )
       }
        composable(route=NavScreens.OnboardingNavHostingScreen.route){
            OnboardingNavHostingScreen(getNavigate = {
                navController.navigate(NavScreens.BottomNavHostingScreen.route)
            })
        }
    }



}