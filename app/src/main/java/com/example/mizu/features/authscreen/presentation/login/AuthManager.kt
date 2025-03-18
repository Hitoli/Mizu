package com.example.mizu.features.authscreen.presentation.login

import com.example.mizu.utils.Result
import com.example.mizu.utils.Utils
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthManager(private val dispatchersIO:CoroutineDispatcher) {
    private val _authSignUpResult:MutableStateFlow<Result<String>> =MutableStateFlow(Result.Loading)
    val authSignUpResult:StateFlow<Result<String>> get() = _authSignUpResult.asStateFlow()

    private val _authSignInResult:MutableStateFlow<Result<String>> =MutableStateFlow(Result.Loading)
    val authSignInResult:StateFlow<Result<String>> get() = _authSignInResult.asStateFlow()

    private val authScope = CoroutineScope(dispatchersIO + SupervisorJob())

    var auth:FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun authSignUp(email:String, password:String){
        authScope.launch {
            _authSignUpResult.emit(Result.Loading)
            try {
                val result = authSignUpWithEmailAndPassword(email,password)
                _authSignUpResult.emit(result)
            }catch (e:Exception){
             _authSignUpResult.emit(Result.Failure(e.message.toString()))
            }
        }
    }
    private suspend fun authSignUpWithEmailAndPassword(email:String, password:String):Result<String>{
        return suspendCoroutine{ continuation->
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isComplete){
                    Utils.logIt("Sign Up", "Success")
                    continuation.resume(Result.Success(it.result.toString()))
                }else{
                    Utils.logIt("Sign Up", "Failed ${it.exception}")
                    continuation.resumeWithException(it.exception?:Exception("FAILED"))
                }
            }
        }
    }

    suspend fun authSignIn(email:String, password: String){
        authScope.launch {
            _authSignInResult.emit(Result.Loading)
            try {
                val result = authSignInWithEmailAndPassword(email,password)
                _authSignInResult.emit(result)
            }catch (e:Exception){
                _authSignInResult.emit(Result.Failure(e.message.toString()))
            }
        }
    }
    private suspend fun authSignInWithEmailAndPassword(email:String, password:String):Result<String>{
       return suspendCoroutine { continuation->
           auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
               if (it.isComplete){
                   Utils.logIt("Sign Up", "Success")
                   continuation.resume(Result.Success(it.result.toString()))
               }else{
                   Utils.logIt("Sign Up", "Failed ${it.exception}")
                   continuation.resumeWithException(it.exception?:Exception("FAILED"))
               }
           }
       }
    }


}