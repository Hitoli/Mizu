package com.example.mizu.presentation_app.navmap

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.onboarding.presentation.activityMeasurement.OnBoardingActiveScreen
import com.example.mizu.features.onboarding.presentation.resultMeasurement.OnBoardingWaterIntakeResultScreen
import com.example.mizu.features.onboarding.presentation.bodyMeasurement.OnBoardingBodyMeasurementsScreen
import com.example.mizu.features.onboarding.presentation.permissionScreens.OnboardingNotifications
import com.example.mizu.features.onboarding.presentation.permissionScreens.OnboardingReminder
import com.example.mizu.features.onboarding.presentation.premium.PremiumScreen
import com.example.mizu.features.onboarding.utils.OnboardingLoadingScreen
import com.example.mizu.features.onboarding.utils.ActivityMeasurementData
import com.example.mizu.features.onboarding.utils.BodyMeasurementData
import com.example.mizu.features.onboarding.utils.PremiumData
import com.example.mizu.features.onboarding.viewModel.OnboardingViewModel
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.waterColorBackground
import com.example.mizu.ui.theme.waterColorMeter
import com.example.mizu.presentation_app.navmap.nav_utils.OnboardingNavScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingNavHostingScreen(
    navController: NavHostController = rememberNavController(),
    getNavigate: () -> Unit,
    onboardingViewModel: OnboardingViewModel = koinViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = OnboardingNavScreens.BodyMeasurementScreen.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(300)
            )
        }
    ) {
        composable(route = OnboardingNavScreens.LoadingScreen.route) {
            OnboardingLoadingScreen(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY),
                        colors = mutableListOf(waterColorBackground, backgroundColor2)
                    )
                ), getNavigate = {
                navController.navigate(OnboardingNavScreens.WaterIntakeResultScreen.route) {
                    popUpTo(OnboardingNavScreens.LoadingScreen.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(route = OnboardingNavScreens.ActivityIntakeScreen.route) {
            OnBoardingActiveScreen(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY),
                        colors = mutableListOf(waterColorBackground, backgroundColor2)
                    )
                ),
                getActiveOutcome = {
                    onboardingViewModel.onBoardingActiveScreen(it)
                },
                activityMeasurementData = ActivityMeasurementData(
                    onErrorText = onboardingViewModel.onActivityLevelError,
                    onActivityOutcome = onboardingViewModel.onActiveValue ?: -1,
                    checkError = onboardingViewModel.onActivityLevelCheck
                ),
                getNavigate = {
                    onboardingViewModel.checkActivtyLevels()
                    if (!onboardingViewModel.onActivityLevelCheck) {
                        if (onboardingViewModel.onActiveValue != Int.MIN_VALUE) {
                            onboardingViewModel.calculateWaterIntake()
                            navController.navigate(OnboardingNavScreens.LoadingScreen.route)
                        }
                    }

                }, getBacK = {
                    navController.navigateUp()
                    navController.popBackStack(
                        OnboardingNavScreens.ActivityIntakeScreen.route,
                        inclusive = true
                    )
                })
        }
        composable(route = OnboardingNavScreens.WaterIntakeResultScreen.route) {
            OnBoardingWaterIntakeResultScreen(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY),
                        colors = mutableListOf(waterColorBackground, backgroundColor2)
                    )
                ),
                getNavigate = {
                    onboardingViewModel.updateUserSettings(false)
                    navController.navigate(OnboardingNavScreens.PremiumScreen.route)
                },
                onWaterIntake = "${onboardingViewModel.onWaterAmount} ml",
                getBack = {
                    navController.navigateUp()
                    navController.popBackStack(
                        OnboardingNavScreens.ActivityIntakeScreen.route,
                        false
                    )
                }
            )
        }
        composable(route = OnboardingNavScreens.BodyMeasurementScreen.route) {
            OnBoardingBodyMeasurementsScreen(
                getWeightChange = {
                    onboardingViewModel.onWeightChange(it)
                }, getHeightChange = {
                    onboardingViewModel.onHeightChange(it)
                },
                getNavigate = {
                    onboardingViewModel.checkBodyMeasurementsFields()
                    if (!onboardingViewModel.onWeightCheck && !onboardingViewModel.onHeightCheck) {
                        navController.navigate(OnboardingNavScreens.ActivityIntakeScreen.route)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY),
                            colors = mutableListOf(waterColorBackground, backgroundColor2)
                        )
                    ),
                bodyMeasurementData = BodyMeasurementData(
                    onWeightChange = onboardingViewModel.onWeightValue,
                    onHeightChange = onboardingViewModel.onHeightValue,
                    onWeightCheck = onboardingViewModel.onWeightCheck,
                    onHeightCheck = onboardingViewModel.onHeightCheck,
                    onHeightError = onboardingViewModel.onHeightError,
                    onWeightError = onboardingViewModel.onWeightError
                )
            )
        }

        composable(route = OnboardingNavScreens.NotificationPermissionScreen.route) {
            OnboardingNotifications(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY),
                        colors = mutableListOf(waterColorBackground, backgroundColor2)
                    )
                ), getAllow = {
                getNavigate()
            }, onPermissionDenied = onboardingViewModel.onNotificationPermissionDenied?:false, getPermissionDenied = {
                onboardingViewModel.updatePermissionNotification(it)
            }
            )
        }

        composable(route = OnboardingNavScreens.ReminderPermissionScreen.route) {
            OnboardingReminder(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY),
                        colors = mutableListOf(waterColorBackground, backgroundColor2)
                    )
                ), getAllow = {
                navController.navigate(OnboardingNavScreens.NotificationPermissionScreen.route)
            }, onPermissionDenied = false
            )
        }

        composable(route = OnboardingNavScreens.PremiumScreen.route) {
            PremiumScreen(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY),
                        colors = mutableListOf(waterColorMeter.copy(alpha = 0.2f), backgroundColor2)
                    )
                ), getBack = {
                navController.navigateUp()
            }, getSubscribe = {
                navController.navigate(OnboardingNavScreens.ReminderPermissionScreen.route)
            }, getSkip = {
                navController.navigate(OnboardingNavScreens.ReminderPermissionScreen.route)
            }, onPremiumData = PremiumData(
                onMonthlyPrice = "29.9", onLifeTimePrice = "99.9", onListOfPremiumBenefits =
                mutableListOf(
                    "Get Access to Home Screen Widget",
                    "Set Custom Daily Reminders",
                    "Advanced Tracking and Analytics",
                    "Ad-Free Experience"
                )
            )
            )
        }
    }


}