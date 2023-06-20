package com.example.mikailovproject.feature.finalproject.registrationFragment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.PostRegistrationAuthUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import java.util.regex.Pattern
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationAuthUseCase: PostRegistrationAuthUseCase
) : ViewModel() {

    private val _state: MutableLiveData<RegistrationState> = MutableLiveData<RegistrationState>()
    val state: LiveData<RegistrationState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException -> handleError(throwable)
            is IllegalArgumentException -> handleError(throwable)
        }
    }

    private fun handleError(throwable: Throwable) {
        _state.value = RegistrationState.Error(throwable)
    }

    fun clear() {
        _state.value = RegistrationState.Clear
    }

    fun signUp(name: String, password: String) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = RegistrationState.Loading
            withContext(Dispatchers.IO) {
                registrationAuthUseCase.invoke(name, password).onSuccess {
                    withContext(Dispatchers.Main) {
                        _state.value = RegistrationState.Success
                    }
                }.onFailure { throwable ->
                    withContext(Dispatchers.Main) {
                        _state.value = RegistrationState.Error(throwable)
                    }
                }
            }
        }
    }

    fun validateLogin(name: String): Boolean = when {
        name.isEmpty() -> false
        Pattern.compile("^[A-ZА-ЯЁ][a-z,а-я-'ё]{0,19}$").matcher(name).find() -> true
        else -> false
    }

    fun validatePassword(password: String): Boolean = when {
        password.isEmpty() -> false
        Pattern.compile("^(?=.*\\d)(?=.*[a-z,а-я])(?=.*[A-ZА-ЯЁ])(?=.*[@#$%^&+=!?])(?=\\S+$).{4,25}$")
            .matcher(password).find() -> true
        else -> false
    }
}