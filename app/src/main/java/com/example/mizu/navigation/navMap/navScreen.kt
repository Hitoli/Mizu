package com.example.mizu.navigation.navMap

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.util.unpackInt1
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
import com.example.mizu.utils.Utils
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun NavScreen(
    authViewModelCommon: authViewModelCommon = koinViewModel(),
    OnboardingViewModel: OnboardingViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel(),
    caledarViewModel: CalendarViewModel = koinViewModel(),
    sharedPreferences: SharedPreferences = koinInject()
) {
    val TAG = "NavScreen"
    val navController = rememberNavController()
    var startDestination by remember {
        mutableStateOf(NavScreens.SplashNavHostingScreen.route)
    }
    authViewModelCommon.updateAuthManagerContext(LocalContext.current)
    val isUserSignedIn by authViewModelCommon.authIsUserSignedIn.collectAsStateWithLifecycle()
    val isUserLoggedOut by authViewModelCommon.authSignOut.collectAsStateWithLifecycle()
    val isOnboardingCompleted = sharedPreferences.getBoolean("onBoardingCompleted", false)

    LaunchedEffect(Unit) {
        if (!isOnboardingCompleted) {
            startDestination = NavScreens.OnboardingNavHostingScreen.route
        } else {
            authViewModelCommon.getIsUserSignedIn()
        }
        Utils.logIt(TAG, "onboarding Completed ${startDestination}")
    }

    LaunchedEffect(isUserSignedIn) {
        when (isUserSignedIn) {
            is Result.Failure -> {
                Utils.logIt(TAG, "Failure User Signed In")
                startDestination = NavScreens.AuthNavHostingScreen.route
            }

            is Result.Loading -> {
                Utils.logIt(TAG, "Loading User Signed In")
            }

            is Result.Success -> {
                val isSignedIn = (isUserSignedIn as Result.Success<Boolean>).data
                Utils.logIt(TAG, "User is Signed In ${isSignedIn}")
                if (isSignedIn) {
                    startDestination = NavScreens.BottomNavHostingScreen.route
                } else {
                    startDestination = NavScreens.AuthNavHostingScreen.route
                }
            }
        }
    }

    LaunchedEffect(isUserLoggedOut) {
        when (isUserLoggedOut) {
            is Result.Failure -> {
                Utils.logIt(TAG, "Failure User Logged Out")
            }

            is Result.Loading -> {
                Utils.logIt(TAG, "Loading User Logged Out")
            }

            is Result.Success -> {
                val signedOut = (isUserLoggedOut as Result.Success<Boolean>).data
                Utils.logIt(TAG, "Is Signed out or not -> ${signedOut}")
                if (signedOut) {
                    startDestination = NavScreens.AuthNavHostingScreen.route
                }
            }
        }
    }



    Utils.logIt(TAG, "onboarding Completed ${startDestination}")

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

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
        composable(route = NavScreens.AuthNavHostingScreen.route) {
            AuthNavHostingScreen(getNavigate = {
                navController.navigate(NavScreens.BottomNavHostingScreen.route) {
                    popUpTo(NavScreens.AuthNavHostingScreen.route) {
                        inclusive = true
                    }
                }
            })
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




