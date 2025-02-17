package com.example.mizu.presentation_app.navmap

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import com.example.mizu.features.authscreen.presentation.login.LoginScreen
import com.example.mizu.features.authscreen.presentation.signup.SignUpScreen
import com.example.mizu.features.authscreen.utils.LoginData
import com.example.mizu.features.authscreen.utils.SignUpData
import com.example.mizu.presentation_app.navmap.nav_utils.AuthNavScreens
import com.example.mizu.presentation_app.navmap.nav_utils.OnboardingNavScreens
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.waterColorBackground

@Composable
fun AuthNavHostingScreen(
    modifier: Modifier = Modifier,
    getNavigate:()->Unit,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = AuthNavScreens.LoginScreen.route,
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
        composable(route = AuthNavScreens.LoginScreen.route) {
            LoginScreen(
                loginData = LoginData(
                    onEmailError = "",
                    onEmail = "",
                    onPasswordError = "",
                    onPassword = "",
                    onPasswordCheckError = false,
                    onEmailCheckError = false
                ),
                getEmailChange = {},
                getPasswordChange = {},
                getLogin = {
                           getNavigate()
                },
                getForgotPassword = { /*TODO*/ },
                getLoginWithGoogle = { /*TODO*/ }, getSignUpNavigate = {
                    navHostController.navigate(AuthNavScreens.SignUpScreen.route)
                }, modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY),
                            colors = listOf(waterColorBackground, backgroundColor2)
                        )
                    )
            )
        }
        composable(route = AuthNavScreens.SignUpScreen.route) {
            SignUpScreen(
                signUpData = SignUpData(
                    onEmailCheckError = false,
                    onPasswordCheckError = false,
                    onPassword = "",
                    onPasswordError = "",
                    onEmailError = "",
                    onEmail = "",
                    onConfirmPasswordError = "",
                    onConfirmPassword = "",
                    onConfirmPasswordCheckError = false
                ),
                getEmailChange = {},
                getPasswordChange = {},
                getConfirmPasswordChange = {},
                getSignUp = {
                    getNavigate()
                },
                getSignUpWithGoogle = { /*TODO*/ }, getLoginInNavigate = {
                    navHostController.navigate(AuthNavScreens.LoginScreen.route)
                }, modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY),
                            colors = listOf(waterColorBackground, backgroundColor2)
                        )
                    )
            )
        }
    }
}

