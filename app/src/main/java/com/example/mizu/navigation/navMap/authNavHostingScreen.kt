package com.example.mizu.navigation.navMap

import android.content.SharedPreferences
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mizu.features.authscreen.login.LoginScreen
import com.example.mizu.features.authscreen.login.loginViewModel
import com.example.mizu.features.authscreen.signup.SignUpScreen
import com.example.mizu.features.authscreen.signup.signUpViewModel
import com.example.mizu.features.authscreen.utils.loginData
import com.example.mizu.features.authscreen.utils.signUpData
import com.example.mizu.navigation.navUtils.AuthNavScreens
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.waterColorBackground
import com.example.mizu.utils.Result
import com.example.mizu.utils.utils
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthNavHostingScreen(
    modifier: Modifier = Modifier,
    getNavigate: () -> Unit,
    navHostController: NavHostController = rememberNavController(),
    loginViewModel: loginViewModel = koinViewModel(),
    signUpViewModel: signUpViewModel = koinViewModel(),
) {
    val TAG = "AuthNavHostingScreen"

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

            val authSignIn by loginViewModel.authSignIn.collectAsStateWithLifecycle()
            val authGoogleSignIn by loginViewModel.authGoogleSignIn.collectAsStateWithLifecycle()
            LaunchedEffect(authSignIn) {
                when (authSignIn) {
                    is Result.Failure -> {
                        val  authSignInException = (authSignIn as Result.Failure<String>).exception
                        utils.logIt(TAG, "Failure Auth Sign In ${authSignInException}")
                        loginViewModel.checkOnAuthException(authSignInException)
                    }
                    is Result.Loading -> {
                        utils.logIt(TAG, "Loading Auth Sign In")
                    }
                    is Result.Success -> {
                        val authResultsignIn = (authSignIn as Result.Success<String>).data
                        utils.logIt(TAG, " Success Auth Sign In ${authResultsignIn}")
                        loginViewModel.checkOnAuthException("")
                        if (authResultsignIn == "Success"){
                            getNavigate()
                        }
                    }
                }
            }

            LaunchedEffect(authGoogleSignIn) {
                when(authGoogleSignIn){
                    is Result.Failure ->{
                        utils.logIt(TAG, "Failure Google Sign In")
                    }
                    is Result.Loading ->{
                        utils.logIt(TAG, "Loading Google Sign In")
                    }
                    is Result.Success -> {
                        val signIn = (authGoogleSignIn as Result.Success<Boolean>).data
                        utils.logIt(TAG, "Success Google Sign In ${signIn}")
                        if (signIn) {
                            getNavigate()
                        }
                    }
                }
            }

            LoginScreen(
                loginData = loginData(
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
                        loginViewModel.authSignInWithEmailAndPassword()
                    }
                },
                getForgotPassword = {
                    loginViewModel.forgotPassword()
                },
                getLoginWithGoogle = {
                    loginViewModel.getLoginWithGoogle()
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
            val authSignUp by signUpViewModel.authSignUp.collectAsStateWithLifecycle()
            when (authSignUp) {
                is Result.Failure -> {
                    utils.logIt("Failure Sign UP", "Failure")
                    val authSignUpException = (authSignUp as Result.Failure<String>).exception
                    signUpViewModel.checkOnAuthException(authSignUpException)
                }
                is Result.Loading -> {
                    utils.logIt("Loading Sign UP", "Loading")
                }
                is Result.Success -> {
                    val authSignUpResult = (authSignUp as Result.Success<String>).data
                    signUpViewModel.checkOnAuthException("")
                    utils.logIt("Success authSignUp", " Success ${authSignUpResult}")
                    getNavigate()
                }
            }

            val authGoogleSignUp by signUpViewModel.authGoogleSignUp.collectAsStateWithLifecycle()
            when(authGoogleSignUp){
                is Result.Failure ->{
                    utils.logIt("Failure Sign UP", "Failure")
                }
                is Result.Loading ->{
                    utils.logIt("Loading Sign UP", "Loading")
                }
                is Result.Success -> {
                    val authGoogleSignUpResult = (authGoogleSignUp as Result.Success<Boolean>).data
                    utils.logIt("Success authGoogleSignUp", " Success ${authGoogleSignUpResult}")
                    if(authGoogleSignUpResult){
                        getNavigate()
                    }
                }
            }
            SignUpScreen(
                signUpData = signUpData(
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
                            colors = mutableListOf(waterColorBackground, backgroundColor2)
                        )
                    )
            )
        }
    }
}

