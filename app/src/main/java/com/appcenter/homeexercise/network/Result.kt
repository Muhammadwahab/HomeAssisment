package com.appcenter.homeexercise.network
sealed class Result<T>(val data: T?) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(val message: String="Some thing went wrong", data: T? = null) : Result<T>(data)
    class Loading<T>(data: T?) : Result<T>(data)
}