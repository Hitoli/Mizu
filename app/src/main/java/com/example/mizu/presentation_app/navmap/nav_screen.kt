package com.example.mizu.presentation_app.navmap

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.homescreen.presentation.HomeScreen
import com.example.mizu.features.homescreen.view_model.HomeViewModel
import com.example.mizu.features.onboarding.view_model.OnboardingViewModel
import com.example.mizu.features.splash_screen.SplashScreen
import com.example.mizu.utils.NavScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavScreen(
    OnboardingViewModel: OnboardingViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel()
) {
    var navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnboardingViewModel.onBoardingScreensRoutes
    ) {
        composable(route = NavScreens.BottomNavHostingScreen.route) {
            BottomBarHostingScreen(
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
                }, items = listOf(50,100,200,300,400,500)
            )
        }
        composable(route = NavScreens.OnboardingNavHostingScreen.route) {
            OnboardingNavHostingScreen(getNavigate = {
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