package com.example.mizu.features.authscreen.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mizu.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class authViewModelCommon( private val dispatcherIO:CoroutineDispatcher, private val authRepositoryCommon: authRepositoryCommon):ViewModel() {

    val authIsUserSignedIn:StateFlow<Result<Boolean>> get() = authRepositoryCommon.authIsUserSignedIn

    val authSignOut:StateFlow<Result<Boolean>> get() = authRepositoryCommon.authGoogleSignOut

    fun getIsUserSignedIn(){
        viewModelScope.launch(dispatcherIO) {
            authRepositoryCommon.authIsUserSignedIn()
        }
    }
    fun authSignOut(){
        viewModelScope.launch {
            authRepositoryCommon.authSignOut()
        }
    }
}