package com.example.mizu.presentation_app.navmap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
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
import com.example.mizu.features.onboarding.utils.OnboardingLoadingScreen
import com.example.mizu.features.onboarding.utils.ActivityMeasurementData
import com.example.mizu.features.onboarding.utils.BodyMeasurementData
import com.example.mizu.features.onboarding.viewModel.OnboardingViewModel
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.waterColorMeter
import com.example.mizu.utils.nav_utils.OnboardingNavScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingNavHostingScreen(
    navController: NavHostController = rememberNavController(),
    getNavigate: () -> Unit,
    onboardingViewModel: OnboardingViewModel = koinViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = OnboardingNavScreens.BodyMeasurementScreen.route
    ) {
        composable(route = OnboardingNavScreens.LoadingScreen.route) {
            OnboardingLoadingScreen(getNavigate = {
                navController.navigate(OnboardingNavScreens.WaterIntakeResultScreen.route)
            })
        }
        composable(route = OnboardingNavScreens.ActivityIntakeScreen.route) {
            OnBoardingActiveScreen(
                getActiveOutcome = {
                    if (it != null) {
                        onboardingViewModel.onBoardingActiveScreen(it)
                    }
                },
                activityMeasurementData = ActivityMeasurementData(
                    onUserName = "",
                    onErrorText = "",
                    onActivityOutcome = 0,
                    checkError = false
                ),
                getNavigate = {
                    if (onboardingViewModel.onActiveValue != Int.MIN_VALUE) {
                        onboardingViewModel.calculateWaterIntake()
                    }
                    navController.navigate(OnboardingNavScreens.LoadingScreen.route)
                })
        }
        composable(route = OnboardingNavScreens.WaterIntakeResultScreen.route) {
            OnBoardingWaterIntakeResultScreen(
                getNavigate = {
                    onboardingViewModel.updateUserSettings()
                    getNavigate()
                },
                onWaterIntake = (onboardingViewModel.TWI).toString(),
                onName = onboardingViewModel.onNameValue
            )
        }
        composable(route = OnboardingNavScreens.BodyMeasurementScreen.route) {
            OnBoardingBodyMeasurementsScreen(
                getWeightChange = {
                    if (it != null) {

                    }
                }, getHeightChange = {

                },
                getNavigate = {
                    navController.navigate(OnboardingNavScreens.ActivityIntakeScreen.route)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY),
                            colors = listOf(waterColorMeter.copy(alpha = 0.4f), backgroundColor2)
                        )
                    ),
                bodyMeasurementData = BodyMeasurementData(
                    onWeightChange = "",
                    onHeightChange = "",
                    onWeightCheck = false,
                    onHeightCheck = false,
                    onHeightError = "",
                    onWeightError = ""
                )
            )
        }
    }


}