package com.example.mizu.utils


sealed class Result<out T> {
    data class Success<T>(var data:T):Result<T>()
    data class Failure<T>(var exception:T):Result<T>()
    data object Loading:Result<Nothing>()
}