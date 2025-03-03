package com.example.mizu.features.authscreen.utils

data class LoginData(
    val onEmail: String,
    val onPassword: String,
    val onEmailError:String,
    val onPasswordError:String,
    val onEmailCheckError:Boolean,
    val onPasswordCheckError:Boolean,
)
