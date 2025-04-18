package com.example.mizu.navigation.navMap

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.authscreen.common.authViewModelCommon
import com.example.mizu.features.calendarscreen.CalendarViewModel
import com.example.mizu.features.homescreen.HomeViewModel
import com.example.mizu.features.onboarding.viewModel.OnboardingViewModel
import com.example.mizu.features.splash_screen.SplashScreen
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.waterColorBackground
import com.example.mizu.navigation.navUtils.NavScreens
import com.example.mizu.utils.Result
import com.example.mizu.utils.utils
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavScreen(
    OnboardingViewModel: OnboardingViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel(),
    caledarViewModel: CalendarViewModel = koinViewModel(),
    authViewModelCommon: authViewModelCommon = koinViewModel()
) {
    val TAG = "NavScreen"
    val navController = rememberNavController()
    authViewModelCommon.getIsUserSignedIn()
    val isUserSignedIn by authViewModelCommon.authIsUserSignedIn.collectAsStateWithLifecycle()
    val isUserLoggedOut by authViewModelCommon.authSignOut.collectAsStateWithLifecycle()

    LaunchedEffect(isUserSignedIn) {
        when (isUserSignedIn) {
            is Result.Failure -> {
                utils.logIt(TAG, "Failure User Signed In")
                navController.navigate(NavScreens.AuthNavHostingScreen.route) {
                    popUpTo(NavScreens.BottomNavHostingScreen.route) {
                        inclusive = true
                    }
                }
            }

            is Result.Loading -> {
                utils.logIt(TAG, "Loading User Signed In")
            }

            is Result.Success -> {
                utils.logIt(TAG, "Success User is Signed In")
                val isSignedIn = (isUserSignedIn as Result.Success<Boolean>).data
                if (isSignedIn) {
                    navController.navigate(NavScreens.BottomNavHostingScreen.route) {
                        popUpTo(NavScreens.AuthNavHostingScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(isUserLoggedOut) {
        when (isUserLoggedOut) {
            is Result.Failure -> {
                utils.logIt(TAG, "Failure User Signed In")
            }

            is Result.Loading -> {
                utils.logIt(TAG, "Loading User Signed In")
            }

            is Result.Success -> {
                val signedOut = (isUserLoggedOut as Result.Success<Boolean>).data
                utils.logIt(TAG, "Is Signed out or not -> ${signedOut}")
                if (signedOut) {
                    navController.navigate(NavScreens.AuthNavHostingScreen.route) {
                        popUpTo(NavScreens.BottomNavHostingScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }



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
            }, getLoginNavigate = {
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
                items = mutableListOf(50, 100, 200, 300, 400, 500),
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
                onHeight = caledarViewModel.height, getLogout = {
                    authViewModelCommon.authSignOut()
                }
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
                            colors = mutableListOf(waterColorBackground, backgroundColor2)
                        )
                    )
            )
        }
    }
}




