package com.example.mikailovproject.feature.finalproject.registrationFragment.presentation

sealed class RegistrationState {
    object Loading : RegistrationState()
    object Success : RegistrationState()
    data class Error(val exception: Throwable) : RegistrationState()
    object Clear : RegistrationState()
}