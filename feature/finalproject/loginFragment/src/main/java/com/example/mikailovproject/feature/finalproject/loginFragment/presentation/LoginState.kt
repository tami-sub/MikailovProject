package com.example.mikailovproject.feature.finalproject.loginFragment.presentation

sealed class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val exception: Throwable) : LoginState()
    object Clear : LoginState()
}