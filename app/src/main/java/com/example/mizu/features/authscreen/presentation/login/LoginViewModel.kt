package com.example.mizu.features.authscreen.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel():ViewModel() {
    var onEmail by mutableStateOf("")
        private set

    var onPassword by mutableStateOf("")
        private set

    var onEmailError by mutableStateOf("")
        private set

    var onPasswordError by mutableStateOf("")
        private set

    var onEmailErrorCheck by mutableStateOf(false)
        private set

    var onPasswordErrorCheck by mutableStateOf(false)
        private set

    fun getEmail(email:String){
        onEmail = email
    }
    fun getPassword(password:String){
        onPassword = password
    }

    fun checkLoginValidation(){
        if (onEmail.isBlank()){
            onEmailError = "Something is wrong with email"
            onEmailErrorCheck = true
        }else{
            onEmailError=""
            onEmailErrorCheck = false
        }
        if (onPassword.isBlank()){
            onPasswordError ="Something is wrong with password"
            onPasswordErrorCheck = true
        }else{
            onPasswordError =""
            onPasswordErrorCheck = false
        }
    }

    fun forgotPassword(){

    }

    fun loginWithGoogle(){

    }
}