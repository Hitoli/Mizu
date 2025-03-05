package com.example.mizu.features.authscreen.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel:ViewModel() {

    var onEmail by mutableStateOf("")
        private set

    var onPassword by mutableStateOf("")
        private set

    var onConfirmPassword by mutableStateOf("")
        private set

    var onEmailError by mutableStateOf("")
        private set

    var onPasswordError by mutableStateOf("")
        private set

    var onConfirmPasswordError by mutableStateOf("")
        private set

    var onEmailErrorCheck by mutableStateOf(false)
        private set

    var onPasswordErrorCheck by mutableStateOf(false)
        private set

    var onConfirmPasswordErrorCheck by mutableStateOf(false)
        private set
    fun getEmail(email:String){
        onEmail = email
    }
    fun getPassword(password:String){
        onPassword = password
    }

    fun getConfirmPassword(confirmPassword:String){
        onConfirmPassword = confirmPassword
    }


    fun checkLoginValidation(){
        if (onEmail.isBlank() && !onEmail.contains("@")){
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

        if (onConfirmPassword.isBlank()){
            onConfirmPasswordError="Something is wrong with confirm password"
            onConfirmPasswordErrorCheck = true
        }else{
            onConfirmPasswordErrorCheck = false
            onConfirmPasswordError=""
        }
    }

    fun getSignUpWithGoogle(){

    }
}