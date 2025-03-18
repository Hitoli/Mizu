package com.example.mizu.presentation_app.navmap

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.authscreen.presentation.login.LoginScreen
import com.example.mizu.features.authscreen.presentation.login.LoginViewModel
import com.example.mizu.features.authscreen.presentation.signup.SignUpScreen
import com.example.mizu.features.authscreen.presentation.signup.SignUpViewModel
import com.example.mizu.features.authscreen.utils.LoginData
import com.example.mizu.features.authscreen.utils.SignUpData
import com.example.mizu.presentation_app.navmap.nav_utils.AuthNavScreens
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.waterColorBackground
import com.example.mizu.utils.Result
import com.example.mizu.utils.Utils
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthNavHostingScreen(
    modifier: Modifier = Modifier,
    getNavigate: () -> Unit,
    navHostController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel = koinViewModel(),
    signUpViewModel: SignUpViewModel = koinViewModel(),
) {

    NavHost(
        modifier = modifier,
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
                    onEmailError = loginViewModel.onEmailError,
                    onEmail = loginViewModel.onEmail,
                    onPasswordError = loginViewModel.onPasswordError,
                    onPassword = loginViewModel.onPassword,
                    onPasswordCheckError = loginViewModel.onPasswordErrorCheck,
                    onEmailCheckError = loginViewModel.onEmailErrorCheck
                ),
                getEmailChange = {
                    loginViewModel.getEmail(it)
                },
                getPasswordChange = {
                    loginViewModel.getPassword(it)
                },
                getLogin = {
                    loginViewModel.checkLoginValidation()
                    if (!loginViewModel.onEmailErrorCheck && !loginViewModel.onPasswordErrorCheck) {
                        getNavigate()
                    }
                },
                getForgotPassword = {
                    loginViewModel.forgotPassword()
                },
                getLoginWithGoogle = {
                    loginViewModel.loginWithGoogle()
                }, getSignUpNavigate = {
                    navHostController.navigate(AuthNavScreens.SignUpScreen.route)
                }, modifier = Modifier
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
        composable(route = AuthNavScreens.SignUpScreen.route) {
            val authResult by signUpViewModel.authSignUp.collectAsState()
            when (authResult) {
                is Result.Failure -> {
                    Utils.logIt("Failure Sign UP", "Failure")
                }
                is Result.Loading -> {
                    Utils.logIt("Failure Sign UP", "Loading")
                }
                is Result.Success -> {
                    Utils.logIt("Failure Sign UP", "Success")
                    getNavigate()
                }
            }
            SignUpScreen(
                signUpData = SignUpData(
                    onEmailCheckError = signUpViewModel.onEmailErrorCheck,
                    onPasswordCheckError = signUpViewModel.onPasswordErrorCheck,
                    onPassword = signUpViewModel.onPassword,
                    onPasswordError = signUpViewModel.onPasswordError,
                    onEmailError = signUpViewModel.onEmailError,
                    onEmail = signUpViewModel.onEmail,
                    onConfirmPasswordError = signUpViewModel.onConfirmPasswordError,
                    onConfirmPassword = signUpViewModel.onConfirmPassword,
                    onConfirmPasswordCheckError = signUpViewModel.onConfirmPasswordErrorCheck
                ),
                getEmailChange = {
                    signUpViewModel.getEmail(it)
                },
                getPasswordChange = {
                    signUpViewModel.getPassword(it)
                },
                getConfirmPasswordChange = {
                    signUpViewModel.getConfirmPassword(it)
                },
                getSignUp = {
                    signUpViewModel.checkLoginValidation()
                    if (!signUpViewModel.onEmailErrorCheck && !signUpViewModel.onPasswordErrorCheck && !signUpViewModel.onConfirmPasswordErrorCheck) {
                        signUpViewModel.authSignUpWithEmailAndPassword()
//                        getNavigate()
                    }
                },
                getSignUpWithGoogle = {
                    signUpViewModel.getSignUpWithGoogle()
                },
                getLoginInNavigate = {
                    navHostController.navigate(AuthNavScreens.LoginScreen.route)
                },
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

