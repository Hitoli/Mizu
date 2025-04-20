package com.example.mizu.features.authscreen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mizu.features.authscreen.login.source.LoginRepository
import com.example.mizu.features.authscreen.common.authRepositoryCommon
import com.example.mizu.utils.Result
import com.example.mizu.utils.Utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class loginViewModel(private val dispatcherIO:CoroutineDispatcher, private val loginRepository: LoginRepository, private val authRepositoryCommon: authRepositoryCommon):ViewModel() {
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

    val authSignIn:StateFlow<Result<String>> get()= loginRepository.authSignIn

    val authGoogleSignIn:StateFlow<Result<Boolean>> get() = authRepositoryCommon.authGoogleSignIn



    fun getEmail(email:String){
        onEmail = email
    }
    fun getPassword(password:String){
        onPassword = password
    }



    fun authSignInWithEmailAndPassword(){
        viewModelScope.launch(dispatcherIO) {
               loginRepository.authSignInWithEmailAndPassword(onEmail,onPassword)
        }
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

    fun checkOnAuthException(exception: String){
        onPasswordError = exception
        onPasswordErrorCheck = exception!=""
    }

    fun forgotPassword(){

    }

    fun getLoginWithGoogle(){
        Utils.logIt("getSignUpWithGoogle", "Clicked")
        viewModelScope.launch(dispatcherIO) {
            authRepositoryCommon.authGoogleSignIn()
        }
    }
}