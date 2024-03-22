package com.example.mizu.presentation_app.navmap

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.onboarding.presentation.OnBoardingActiveScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingHeightScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingWaterIntakeResultScreen
import com.example.mizu.features.onboarding.presentation.OnBoardingWeightScreen
import com.example.mizu.features.onboarding.presentation.OnboardingLoadingScreen
import com.example.mizu.utils.BottomNavScreens
import com.example.mizu.utils.OnboardingNavScreens

@Composable
fun OnboardingNavHostingScreen(
    navController: NavHostController = rememberNavController(),
    getNavigate:()->Unit
) {
    NavHost(
        navController = navController,
        startDestination = OnboardingNavScreens.NameScreen.route
    ) {
        composable(route = OnboardingNavScreens.NameScreen.route) {
            OnBoardingScreen(
                getValue = {},
                onValue = "",
                onQuestionValue = "",
                getNavigate = { navController.navigate(OnboardingNavScreens.WeightIntakeScreen.route) })
        }
        composable(route = OnboardingNavScreens.LoadingScreen.route) {
            OnboardingLoadingScreen {
                OnboardingNavScreens.WaterIntakeResultScreen.route
            }
        }
        composable(route = OnboardingNavScreens.ActivityIntakeScreen.route) {
            OnBoardingActiveScreen(getActiveOutcome = {}, onActiveOutcome = 0, onUserName = "") {
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
            OnBoardingWeightScreen(getWeightChange = {}, onWeightChange = 0, onUserName = "") {
                println("Naavigationg")
                navController.navigate(
                    OnboardingNavScreens.HeightIntakeScreen.route
                )
            }
        }
        composable(route = OnboardingNavScreens.HeightIntakeScreen.route) {
            OnBoardingHeightScreen(getWeightChange = {}, onWeightChange = 0, onUserName = "") {
                navController.navigate(OnboardingNavScreens.ActivityIntakeScreen.route)
            }
        }
    }


}