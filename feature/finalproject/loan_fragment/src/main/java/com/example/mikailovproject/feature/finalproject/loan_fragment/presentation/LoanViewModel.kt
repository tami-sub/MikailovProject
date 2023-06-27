package com.example.mikailovproject.feature.finalproject.loan_fragment.presentation

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikailovproject.feature.finalproject.loan_fragment.R
import com.example.mikailovproject.network.retrofit.AuthInterceptor
import com.example.mikailovproject.network.retrofit.DomainException
import com.example.mikailovproject.shared.finalproject.core.data.sharedpreferences.AuthTokenManager
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.GetLoanConditionsUseCase
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.PostCreateLoanUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import java.util.regex.Pattern
import javax.inject.Inject

class LoanViewModel @Inject constructor(
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val postCreateLoanUseCase: PostCreateLoanUseCase,
    private val application: Application,
    authTokenManager: AuthTokenManager,
    authInterceptor: AuthInterceptor
) : ViewModel() {
    private var token: String = ""

    private val _state: MutableLiveData<LoanState> = MutableLiveData<LoanState>()
    val state: LiveData<LoanState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException -> handleError(throwable)
            is IllegalArgumentException -> handleError(throwable)
        }
    }

    init {
        getLoanConditions()
    }

    fun getLoanConditions() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = LoanState.Loading
            withContext(Dispatchers.IO) {
                getLoanConditionsUseCase.invoke().onSuccess {
                    withContext(Dispatchers.Main) {
                        _state.value = LoanState.SuccessConditions(
                            it.maxAmount, it.percent, it.period
                        )
                    }
                }.onFailure { throwable ->
                    withContext(Dispatchers.Main) {
                        _state.value = LoanState.Error(throwable, true)
                    }
                }
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        _state.value = LoanState.Error(throwable, false)
    }

    fun validateName(name: String): Boolean = when {
        name.isEmpty() -> false
        Pattern.compile("^[A-ZА-ЯЁ][a-z,а-я-'ё]{0,19}$").matcher(name).find() -> true
        else -> false
    }

    fun validateAmount(amount: String, maxAmount: Long): Boolean = when {
        amount.isEmpty() -> false
        (amount.toLong() <= maxAmount) -> true
        else -> false
    }

    fun validatePhoneNumber(phone: String): Boolean = when {
        phone.isEmpty() -> false
        Patterns.PHONE.matcher(phone).matches() -> true
        else -> false
    }

    fun createLoan(
        amount: Long,
        firstName: String,
        lastName: String,
        percent: Double,
        period: Int,
        phoneNumber: String
    ) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = LoanState.Loading
            withContext(Dispatchers.IO) {
                postCreateLoanUseCase.invoke(
                    amount, firstName, lastName, percent, period, phoneNumber
                ).onSuccess {
                    withContext(Dispatchers.Main) {
                        _state.value = LoanState.SuccessCreation(it)
                    }
                }.onFailure { throwable ->
                    withContext(Dispatchers.Main) {
                        val customException =
                            if (throwable is DomainException.BadRequestException) Exception(
                                application.applicationContext.getString(R.string.relogin)
                            ) else throwable
                        _state.value = LoanState.Error(customException, false)
                    }
                }
            }
        }
    }
}