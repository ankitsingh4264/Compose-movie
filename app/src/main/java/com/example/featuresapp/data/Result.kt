package com.example.featuresapp.data


// this also contains the UI state
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<T>(val msg:String?): Result<T>()
    data object Loading: Result<Nothing>()
    data object Initial: Result<Nothing>()
}