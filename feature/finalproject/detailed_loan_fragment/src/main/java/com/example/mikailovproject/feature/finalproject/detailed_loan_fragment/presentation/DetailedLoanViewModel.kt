package com.example.mikailovproject.feature.finalproject.detailed_loan_fragment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikailovproject.network.retrofit.AuthInterceptor
import com.example.mikailovproject.shared.finalproject.core.data.sharedpreferences.AuthTokenManager
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.GetLoanByIdUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class DetailedLoanViewModel @Inject constructor(
    private val getLoanByIdUseCase: GetLoanByIdUseCase,
    authTokenManager: AuthTokenManager,
    authInterceptor: AuthInterceptor
) : ViewModel() {
    private var token: String = ""

    private val _state: MutableLiveData<DetailedLoanState> = MutableLiveData<DetailedLoanState>()
    val state: LiveData<DetailedLoanState> = _state

    init {
        token = authTokenManager.getAuthToken() ?: ""
        authInterceptor.updateToken(token)
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException -> handleError(throwable)
            is IllegalArgumentException -> handleError(throwable)
        }
    }

    fun getLoanById(id: String) {
        _state.value = DetailedLoanState.Loading
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                getLoanByIdUseCase.invoke(id).onSuccess {
                    withContext(Dispatchers.Main) {
                        _state.value = DetailedLoanState.Success(it)
                    }
                }.onFailure {
                    withContext(Dispatchers.Main) {
                        _state.value = DetailedLoanState.Error(it)
                    }
                }
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        _state.value = DetailedLoanState.Error(throwable)
    }
}