package com.example.mikailovproject.feature.finalproject.secondFragment.presentation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.GetRandomFactFromLocalUseCase
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.GetRandomFactFromRemoteUseCase
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    private val getRandomFactFromLocalUseCase: GetRandomFactFromLocalUseCase,
    private val getRandomFactFromRemoteUseCase: GetRandomFactFromRemoteUseCase,
    ): ViewModel(){

    private val _state: MutableLiveData<SecondState> = MutableLiveData<SecondState>()
    val state: LiveData<SecondState> = _state

    fun loadStrings() {
        _state.value = SecondState.Loading

        val fromLocal = getRandomFactFromLocalUseCase.invoke()
        val fromRemote = getRandomFactFromRemoteUseCase.invoke()

        _state.value = SecondState.Success(remoteString = fromRemote, localString = fromLocal)
    }
}