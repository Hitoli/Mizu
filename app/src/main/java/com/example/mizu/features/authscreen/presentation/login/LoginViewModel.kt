package com.example.mizu.features.authscreen.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mizu.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val dispatcherIO:CoroutineDispatcher,private val loginRepository: LoginRepository):ViewModel() {
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

    fun forgotPassword(){

    }

    fun loginWithGoogle(){

    }
}