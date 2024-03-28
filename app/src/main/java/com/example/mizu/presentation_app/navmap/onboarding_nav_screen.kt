package com.example.mizu.presentation_app.navmap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.yml.charts.common.extensions.isNotNull
import com.example.mizu.features.onboarding.presentation.OnBoardingActiveScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingHeightScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingWaterIntakeResultScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingWeightScreen
import com.example.mizu.features.onboarding.presentation.OnboardingLoadingScreen
import com.example.mizu.features.onboarding.view_model.OnboardingViewModel
import com.example.mizu.utils.BottomNavScreens
import com.example.mizu.utils.OnboardingNavScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingNavHostingScreen(
    navController: NavHostController = rememberNavController(),
    getNavigate:()->Unit,
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
                            onboardingViewModel.onBoardingNameScreen(it)
                        }
                },
                onValue = onboardingViewModel.onNameValue,
                onQuestionValue = "What is your name?",
                getNavigate = { navController.navigate(OnboardingNavScreens.WeightIntakeScreen.route) })
        }
        composable(route = OnboardingNavScreens.LoadingScreen.route) {
            OnboardingLoadingScreen {
                OnboardingNavScreens.WaterIntakeResultScreen.route
            }
        }
        composable(route = OnboardingNavScreens.ActivityIntakeScreen.route) {
            OnBoardingActiveScreen(getActiveOutcome = {
                if (it != null) {
                    onboardingViewModel.onBoardingActiveScreen(it)
                }
            }, onActiveOutcome = onboardingViewModel.onActiveValue, onUserName = onboardingViewModel.onNameValue) {
                getNavigate()
//                navController.navigate(OnboardingNavScreens.LoadingScreen.route)
            }
        }
        composable(route = OnboardingNavScreens.WaterIntakeResultScreen.route) {
            OnBoardingWaterIntakeResultScreen(getNavigate = {
                getNavigate()
            }, onWaterIntake = "")
        }
        composable(route = OnboardingNavScreens.WeightIntakeScreen.route) {
            OnBoardingWeightScreen(getWeightChange = {
                if (it != null) {
                    onboardingViewModel.onBoardingWeightScreen(it)
                }
            }, onWeightChange = onboardingViewModel.onWeightValue, onUserName = onboardingViewModel.onNameValue) {
                navController.navigate(
                    OnboardingNavScreens.HeightIntakeScreen.route
                )
            }
        }
        composable(route = OnboardingNavScreens.HeightIntakeScreen.route) {
            OnBoardingHeightScreen(getHeightChange = {
                if (it != null) {
                    onboardingViewModel.onBoardingHeightScreen(it)
                }
            }, onHeightChange = onboardingViewModel.onHeightValue, onUserName = onboardingViewModel.onNameValue) {
                navController.navigate(OnboardingNavScreens.ActivityIntakeScreen.route)
            }
        }
    }


}