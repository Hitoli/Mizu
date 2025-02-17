package com.example.mizu.presentation_app.navmap.nav_utils

sealed class AuthNavScreens(val route:String){
    data object LoginScreen: AuthNavScreens(route = "Login")
    data object SignUpScreen: AuthNavScreens(route = "SignUp")
}