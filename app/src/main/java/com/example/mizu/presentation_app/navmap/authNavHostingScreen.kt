package com.example.mizu.presentation_app.navmap

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.example.mizu.presentation_app.navmap.nav_utils.NavScreens
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

            loginViewModel.updateAuthManagerContext(LocalContext.current)

            loginViewModel.getIsUserSignedIn()

            val isUserSignedIn by loginViewModel.authIsUserSignedIn.collectAsStateWithLifecycle()
            when (isUserSignedIn) {
                is Result.Failure -> {
                    Utils.logIt("Failure Sign IN", "Failure")
                }
                is Result.Loading -> {
                    Utils.logIt("Loading Sign IN", "Loading")
                }
                is Result.Success -> {

                    val isSignedIn = (isUserSignedIn as Result.Success<Boolean>).data
                    Utils.logIt("Success isSignedIn", "Success ${isSignedIn}")
                    if (isSignedIn) {
                        getNavigate()
                    }
                }
            }
            val authSignIn by loginViewModel.authSignIn.collectAsStateWithLifecycle()
            when (authSignIn) {
                is Result.Failure -> {
                    Utils.logIt("Failure Sign IN", "Failure")
                }
                is Result.Loading -> {
                    Utils.logIt("Loading Sign IN", "Loading")
                }
                is Result.Success -> {
                    val authResultsignIn = (authSignIn as Result.Success<String>).data
                    Utils.logIt("Success authSignIn", " Success ${authResultsignIn}")
                    getNavigate()
                }
            }

            val authGoogleSignIn by loginViewModel.authGoogleSignIn.collectAsStateWithLifecycle()
            when(authGoogleSignIn){
                is Result.Failure ->{
                    Utils.logIt("Failure Sign In", "Failure")
                }
                is Result.Loading ->{
                    Utils.logIt("Loading Sign In", "Loading")
                }
                is Result.Success -> {
                    val signIn = (authGoogleSignIn as Result.Success<Boolean>).data
                    Utils.logIt("Success authGoogleSignIn", " Success ${signIn}")
                    if (signIn) {
                        getNavigate()
                    }
                }
            }

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
                    Utils.logIt("Failure Sign UP", "Failure")
                }
                is Result.Loading -> {
                    Utils.logIt("Loading Sign UP", "Loading")
                }
                is Result.Success -> {
                    val authSignUpResult = (authSignUp as Result.Success<String>).data
                    Utils.logIt("Success authSignUp", " Success ${authSignUpResult}")
                    getNavigate()
                }
            }

            val authGoogleSignUp by signUpViewModel.authGoogleSignUp.collectAsStateWithLifecycle()
            when(authGoogleSignUp){
                is Result.Failure ->{
                    Utils.logIt("Failure Sign UP", "Failure")
                }
                is Result.Loading ->{
                    Utils.logIt("Loading Sign UP", "Loading")
                }
                is Result.Success -> {
                    val authGoogleSignUpResult = (authGoogleSignUp as Result.Success<Boolean>).data
                    Utils.logIt("Success authGoogleSignUp", " Success ${authGoogleSignUpResult}")
                    if(authGoogleSignUpResult){
                        getNavigate()
                    }
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

