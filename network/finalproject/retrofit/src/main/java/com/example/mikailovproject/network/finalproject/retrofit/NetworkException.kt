package com.example.mikailovproject.network.finalproject.retrofit

sealed class NetworkException(message: String) : RuntimeException(message) {
    class IOException(message: String) : NetworkException(message)
    class TimeoutException(message: String) : NetworkException(message)
}
