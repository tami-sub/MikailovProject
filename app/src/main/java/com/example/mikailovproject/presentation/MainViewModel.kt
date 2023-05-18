package com.example.mikailovproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mikailovproject.domain.usecase.GetRandomFactFromLocalUseCase
import com.example.mikailovproject.domain.usecase.GetRandomFactFromRemoteUseCase
import com.example.mikailovproject.presentation.MainState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRandomFactFromLocalUseCase: GetRandomFactFromLocalUseCase,
    private val getRandomFactFromRemoteUseCase: GetRandomFactFromRemoteUseCase,
    ): ViewModel(){

    private val _state: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun loadStrings() {
        _state.value = MainState.Loading

        val fromLocal = getRandomFactFromLocalUseCase.invoke()
        val fromRemote = getRandomFactFromRemoteUseCase.invoke()

        _state.value = MainState.Success(remoteString = fromRemote, localString = fromLocal)
    }
}