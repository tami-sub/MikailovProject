package com.example.mikailovproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mikailovproject.domain.usecase.GetRandomFactFromLocalUseCase
import com.example.mikailovproject.domain.usecase.GetRandomFactFromRemoteUseCase
import com.example.mikailovproject.presentation.MainState

class MainViewModel : ViewModel() {
    //TODO: DI
    private val getRandomFactFromLocalUseCase = GetRandomFactFromLocalUseCase()
    private val getRandomFactFromRemoteUseCase = GetRandomFactFromRemoteUseCase()

    private val _state: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun loadStrings() {
        _state.value = MainState.Loading

        val fromLocal = getRandomFactFromLocalUseCase()
        val fromRemote = getRandomFactFromRemoteUseCase()

        _state.value = MainState.Success(remoteString = fromRemote, localString = fromLocal)
    }
}