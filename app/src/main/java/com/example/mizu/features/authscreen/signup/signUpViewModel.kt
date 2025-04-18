package com.example.mizu.features.authscreen.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mizu.features.authscreen.signup.source.SignUpRepository
import com.example.mizu.features.authscreen.common.authRepositoryCommon
import com.example.mizu.utils.Result
import com.example.mizu.utils.utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class signUpViewModel(private val dispatcherIO: CoroutineDispatcher, private val signUpRepository: SignUpRepository, private val authRepositoryCommon: authRepositoryCommon):ViewModel() {

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

    val authSignUp: StateFlow<Result<String>> get()= signUpRepository.authSignUp
    val authGoogleSignUp:StateFlow<Result<Boolean>> get() = authRepositoryCommon.authGoogleSignIn

    fun getEmail(email:String){
        onEmail = email
    }
    fun getPassword(password:String){
        onPassword = password
    }

    fun getConfirmPassword(confirmPassword:String){
        onConfirmPassword = confirmPassword
    }

    fun checkOnAuthException(exception: String){
        onConfirmPasswordError = exception
        onConfirmPasswordErrorCheck = exception!=""
    }

     fun authSignUpWithEmailAndPassword(){
        viewModelScope.launch(dispatcherIO) {
            signUpRepository.authSignUpWithEmailAndPassword(onEmail,onPassword)
        }
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

        if (onPassword != (onConfirmPassword)){
            onPasswordError = "Passwords do not match"
            onConfirmPasswordError = "Passwords do not match"
            onPasswordErrorCheck = true
            onConfirmPasswordErrorCheck = true
        }else{
            onConfirmPasswordErrorCheck = false
            onPasswordErrorCheck = false
            onPasswordError =""
            onConfirmPassword = ""
        }
        if (onPassword.contains(Regex("[A-Z]")) && onPassword.contains(Regex("\\d")) && onPassword.contains(Regex("[^a-zA-Z0-9]"))){
            onConfirmPasswordErrorCheck = false
            onPasswordErrorCheck = false
            onPasswordError =""
            onConfirmPassword = ""
        }else{
            onPasswordError = "Passwords should have at least 1 Capital letter, 1 Number and 1 special character"
            onPasswordErrorCheck = true
            onConfirmPasswordErrorCheck = true
        }
    }

    fun getSignUpWithGoogle(){
        utils.logIt("getSignUpWithGoogle", "Clicked")
        viewModelScope.launch(dispatcherIO) {
            authRepositoryCommon.authGoogleSignIn()
        }
    }


}