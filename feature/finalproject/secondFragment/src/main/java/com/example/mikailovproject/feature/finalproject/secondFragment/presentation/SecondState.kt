package com.example.mikailovproject.feature.finalproject.secondFragment.presentation

sealed class SecondState {

    object Loading : SecondState()

    data class Success(val remoteString: String, val localString: String) : SecondState()
}