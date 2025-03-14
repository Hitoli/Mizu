package com.example.mizu.presentation_app.navmap

import ProfileScreen
import android.util.Log
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.calendarscreen.view_model.CalendarViewModel
import com.example.mizu.features.homescreen.view_model.HomeViewModel
import com.example.mizu.features.onboarding.viewModel.OnboardingViewModel
import com.example.mizu.features.profilescreen.utils.ProfileData
import com.example.mizu.features.splash_screen.SplashScreen
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.waterColorBackground
import com.example.mizu.ui.theme.waterColorMeter
import com.example.mizu.presentation_app.navmap.nav_utils.NavScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavScreen(
    OnboardingViewModel: OnboardingViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel(),
    caledarViewModel: CalendarViewModel = koinViewModel()
) {
    var navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnboardingViewModel.onBoardingScreensRoutes
    ) {
        composable(route = NavScreens.AuthNavHostingScreen.route) {
            AuthNavHostingScreen(getNavigate = {
                navController.navigate(NavScreens.BottomNavHostingScreen.route) {
                    popUpTo(NavScreens.AuthNavHostingScreen.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(route = NavScreens.BottomNavHostingScreen.route) {
            BottomBarHostingScreen(
                getUpdateTotalWaterTrackingAmount = {
                    OnboardingViewModel.updateOnboardingWaterAmount(it)

                    Log.d(
                        "totalWaterAmount OnboardingViewModel",
                        homeViewModel.totalWaterAmount.toString()
                    )
                },
                onUserName = OnboardingViewModel.onNameValue,
                onWaterTrackingResourceAmount = homeViewModel.usedWaterAmount,
                onTotalWaterTrackingResourceAmount = homeViewModel.totalWaterAmount,
                onReward = homeViewModel.rewardDialog,
                getWaterTrackingResourceAmount = {
                    homeViewModel.fillWaterUpdate(it)

                },
                getReward = {
                    if (it != null) {
                        homeViewModel.DismissReward(it)
                    }
                },
                getAddWater = {

                },
                onWaterMeterResourceAmount = homeViewModel.waterPercent,
                onProgress = homeViewModel.onProgress,
                onStreak = homeViewModel._streak.streak.toString(),
                getStreak = {},
                onTime = homeViewModel.onTime,
                getGreeting = {
                    homeViewModel.getGreeting();
                },
                items = mutableStateListOf(50, 100, 200, 300, 400, 500),
                streakImages = homeViewModel.perks,
                onMonth = caledarViewModel.onMonth,
                onWaterGoals = caledarViewModel.onWaterGoals,
                calendarList = caledarViewModel.calendarList,
                getSelected = {
                    caledarViewModel.updateWaterGoals(it)
                },
                getProfileClick = {
//                    navController.navigate(NavScreens.ProfileNavHostingScreen.route)
                },
                imgModifier = Modifier,
                onAvgIntake = caledarViewModel.avgWaterIntake,
                onWeight = caledarViewModel.weight,
                onBestStreak = caledarViewModel.bestStreak.toString(),
                onHeight = caledarViewModel.height


            )
        }
        composable(route = NavScreens.OnboardingNavHostingScreen.route) {
            OnboardingNavHostingScreen(getNavigate = {
                homeViewModel.updateTotalWaterAmount(OnboardingViewModel.onWaterAmount)
                navController.navigate(NavScreens.AuthNavHostingScreen.route) {
                    popUpTo(NavScreens.OnboardingNavHostingScreen.route) {
                        inclusive = true
                    }
                }
                OnboardingViewModel.updateUserSettings(true)
            }, onboardingViewModel = OnboardingViewModel)
        }
        composable(route = NavScreens.SplashNavHostingScreen.route) {
            SplashScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY),
                            colors = mutableStateListOf(waterColorBackground, backgroundColor2)
                        )
                    )
            )
        }
    }
}



