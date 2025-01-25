package com.example.mizu.presentation_app.navmap

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.onboarding.presentation.OnBoardingActiveScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingHeightScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingWaterIntakeResultScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingBodyMeasurementsScreen
import com.example.mizu.features.onboarding.presentation.OnboardingLoadingScreen
import com.example.mizu.features.onboarding.view_model.OnboardingViewModel
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
        startDestination = OnboardingNavScreens.NameScreen.route
    ) {
        composable(route = OnboardingNavScreens.NameScreen.route) {
            OnBoardingScreen(
                getValue = {
                    if (it != null) {
                        onboardingViewModel.checkFields(it)
                        onboardingViewModel.onBoardingNameScreen(it)
                    }
                },
                onValue = onboardingViewModel.onNameValue,
                onQuestionValue = "What is your name?",
                getNavigate = {

                    onboardingViewModel.checkFields(onboardingViewModel.onNameValue)
                    if (!onboardingViewModel.check) {
                        navController.navigate(OnboardingNavScreens.WeightIntakeScreen.route) {
                            popUpTo(OnboardingNavScreens.NameScreen.route) {
                                inclusive = true
                            }
                        }

                    }
                },
                check = onboardingViewModel.check,
                checkText = "Name field can not be empty or contain number / Special characters"
            )
        }
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
                onActiveOutcome = onboardingViewModel.onActiveValue,
                onUserName = onboardingViewModel.onNameValue,
                check = onboardingViewModel.onActiveValue == Int.MIN_VALUE,
                checkText = "Please enter your acitivity level",
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
        composable(route = OnboardingNavScreens.WeightIntakeScreen.route) {
            OnBoardingBodyMeasurementsScreen(
                getWeightChange = {
                    if (it != null) {
                        onboardingViewModel.checkDigitFields(it)
                        onboardingViewModel.onBoardingWeightScreen(it)
                    }
                },
                onWeightChange = onboardingViewModel.onWeightValue,
                onUserName = onboardingViewModel.onNameValue,
                check = onboardingViewModel.checkDigit,
                checkText = "Field can not be empty or contain words",
                getNavigate = {
                    onboardingViewModel.checkDigitFields(onboardingViewModel.onWeightValue)

                    if (!onboardingViewModel.checkDigit) {
                        navController.navigate(
                            OnboardingNavScreens.HeightIntakeScreen.route
                        ) {
                            popUpTo(OnboardingNavScreens.WeightIntakeScreen.route) {
                                inclusive = true
                            }
                        }
                    }


                })
        }
        composable(route = OnboardingNavScreens.HeightIntakeScreen.route) {
            OnBoardingHeightScreen(
                getHeightChange = {
                    if (it != null) {
                        onboardingViewModel.checkDigitFields(it)
                        onboardingViewModel.onBoardingHeightScreen(it)
                    }
                },
                onHeightChange = onboardingViewModel.onHeightValue,
                onUserName = onboardingViewModel.onNameValue,
                check = onboardingViewModel.checkDigit,
                checkText = "Field can not be empty or contain words",
                getNavigate = {
                    onboardingViewModel.checkDigitFields(onboardingViewModel.onHeightValue)
                    if (!onboardingViewModel.checkDigit) {
                        navController.navigate(OnboardingNavScreens.ActivityIntakeScreen.route) {
                            popUpTo(OnboardingNavScreens.HeightIntakeScreen.route) {
                                inclusive = true
                            }
                        }
                    }

                })
        }
    }


}