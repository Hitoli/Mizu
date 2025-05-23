package com.example.mizu.features.authscreen.utils

interface authInterface {
    suspend fun authSignUpWithEmailAndPassword(email:String, password:String){}
    suspend fun authSignInWithEmailAndPassword(email:String, password:String){}
    suspend fun authGoogleSignIn(){}
    suspend fun authSignOut(){}
    suspend fun authIsUserSignedIn(){}
}