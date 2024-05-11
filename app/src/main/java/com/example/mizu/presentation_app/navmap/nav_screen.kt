package com.example.mizu.presentation_app.navmap

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.calendarscreen.view_model.CalendarViewModel
import com.example.mizu.features.homescreen.presentation.HomeScreen
import com.example.mizu.features.homescreen.view_model.HomeViewModel
import com.example.mizu.features.onboarding.view_model.OnboardingViewModel
import com.example.mizu.features.splash_screen.SplashScreen
import com.example.mizu.utils.NavScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavScreen(
    OnboardingViewModel: OnboardingViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel(),
    caledarViewModel: CalendarViewModel = koinViewModel()
) {
    var navController = rememberNavController()
    Log.d("updateWaterGoals NavScreen",caledarViewModel.onWaterGoals.toList().toString())
    NavHost(
        navController = navController,
        startDestination = OnboardingViewModel.onBoardingScreensRoutes
    ) {
        composable(route = NavScreens.BottomNavHostingScreen.route) {
            Log.d("onTotalWaterTrackingResourceAmount Onboarding",OnboardingViewModel.onWaterAmount.toString())

            BottomBarHostingScreen(
                getUpdateTotalWaterTrackingAmount = {
                    OnboardingViewModel.updateOnboardingWaterAmount(it)

                    Log.d("totalWaterAmount OnboardingViewModel",  homeViewModel.totalWaterAmount.toString())
                },
                onUserName = OnboardingViewModel.onNameValue,
                onWaterTrackingResourceAmount = homeViewModel.usedWaterAmount,
                onTotalWaterTrackingResourceAmount = homeViewModel.totalWaterAmount,
                onReward = homeViewModel.rewardDialog,
                getWaterTrackingResourceAmount = {
                    homeViewModel.fillWaterUpdate(it)

                },
                getReward = {
                    if (it!=null){
                       homeViewModel.DismissReward(it)
                    }
                },
                getAddWater = {

                }, onWaterMeterResourceAmount =homeViewModel.waterPercent, onProgress = homeViewModel.onProgress, onStreak =homeViewModel._streak.streak.toString(), getStreak = {}, onTime = homeViewModel.onTime, getGreeting = {
                    homeViewModel.getGreeting();
                }, items = listOf(50,100,200,300,400,500),
                streakImages = homeViewModel.perks,
                onMonth = caledarViewModel.onMonth,
                onWaterGoals = caledarViewModel.onWaterGoals,
                calendarList = caledarViewModel.calendarList, getSelected = {
                    caledarViewModel.updateWaterGoals(it)
                }

            )
        }
        composable(route = NavScreens.OnboardingNavHostingScreen.route) {
            OnboardingNavHostingScreen(getNavigate = {
                homeViewModel.updateTotalWaterAmount(OnboardingViewModel.onWaterAmount)
                navController.navigate(NavScreens.BottomNavHostingScreen.route) {
                    popUpTo(NavScreens.OnboardingNavHostingScreen.route) {
                        inclusive = true
                    }
                }
            }, onboardingViewModel = OnboardingViewModel)
        }
        composable(route=NavScreens.SplashNavHostingScreen.route){
            SplashScreen()
        }
    }


}