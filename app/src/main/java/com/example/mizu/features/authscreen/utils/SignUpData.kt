package com.example.mizu.features.authscreen.utils

data class SignUpData(
    val onEmail: String,
    val onPassword: String,
    val onConfirmPassword:String,
    val onConfirmPasswordError:String,
    val onConfirmPasswordCheckError:Boolean,
    val onEmailError:String,
    val onPasswordError:String,
    val onEmailCheckError:Boolean,
    val onPasswordCheckError:Boolean
)