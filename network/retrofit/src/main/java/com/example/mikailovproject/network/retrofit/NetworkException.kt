package com.example.mikailovproject.network.retrofit

sealed class NetworkException(message: String) : RuntimeException(message) {
    class IOException(message: String) : NetworkException(message)
    class TimeoutException(message: String) : NetworkException(message)
}
