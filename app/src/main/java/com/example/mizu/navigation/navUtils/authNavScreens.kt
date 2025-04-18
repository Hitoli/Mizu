package com.example.mizu.navigation.navUtils

sealed class AuthNavScreens(val route:String){
    data object LoginScreen: AuthNavScreens(route = "Login")
    data object SignUpScreen: AuthNavScreens(route = "SignUp")
}