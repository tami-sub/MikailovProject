package com.example.mikailovproject.feature.finalproject.history_fragment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.GetAllLoansUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getAllLoansUseCase: GetAllLoansUseCase
) : ViewModel() {
    private var token: String = ""

    private val _state: MutableLiveData<HistoryState> = MutableLiveData<HistoryState>()
    val state: LiveData<HistoryState> = _state

    init {
        getAllLoans()
    }

    fun clear() {
        _state.value = HistoryState.Clear
    }

    fun getAllLoans() {
        _state.value = HistoryState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val allLoans = getAllLoansUseCase.invoke()
            allLoans.first.onSuccess {
                withContext(Dispatchers.Main) {
                    _state.value = HistoryState.Success(allLoans.second)
                }
            }.onFailure {
                withContext(Dispatchers.Main) {
                    _state.value = HistoryState.Error(it, allLoans.second)
                }
            }
        }

//        viewModelScope.launch(exceptionHandler) {
////            withContext(Dispatchers.IO) {
////                _state.value = HistoryState.Success(getAllLoansUseCase.invoke())
////            }
//        }
    }
}