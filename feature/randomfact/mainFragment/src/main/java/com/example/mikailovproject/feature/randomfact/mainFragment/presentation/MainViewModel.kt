package com.example.mikailovproject.feature.randomfact.mainFragment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mikailovproject.feature.randomfact.mainFragment.presentation.MainState
import com.example.mikailovproject.shared.randomfact.core.domain.usecase.GetRandomFactFromLocalUseCase
import com.example.mikailovproject.shared.randomfact.core.domain.usecase.GetRandomFactFromRemoteUseCase
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