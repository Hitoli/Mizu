package com.example.mizu.features.authscreen.utils

interface AuthInterface {
    suspend fun authSignUpWithEmailAndPassword(email:String, password:String){}
    suspend fun authSignInWithEmailAndPassword(email:String, password:String){}
    suspend fun authGoogleSignUp(){}
    suspend fun authGoogleLoginIn(){}
}