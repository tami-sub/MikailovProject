package com.example.mikailovproject.feature.finalproject.login_fragment.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikailovproject.feature.finalproject.login_fragment.R
import com.example.mikailovproject.network.retrofit.AuthInterceptor
import com.example.mikailovproject.network.retrofit.DomainException
import com.example.mikailovproject.shared.finalproject.core.data.sharedpreferences.AuthTokenManager
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.DeleteAllLoansUseCase
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.PostLoginAuthUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import java.util.regex.Pattern
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val postLoginAuthUseCase: PostLoginAuthUseCase,
    private val deleteAllLoansUseCase: DeleteAllLoansUseCase,
    private val authTokenManager: AuthTokenManager,
    private val authInterceptor: AuthInterceptor,
    private val application: Application
) : ViewModel() {

    private val _state: MutableLiveData<LoginState> = MutableLiveData<LoginState>()
    val state: LiveData<LoginState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException -> handleError(throwable)
            is IllegalArgumentException -> handleError(throwable)
        }
    }

    private fun handleError(throwable: Throwable) {
        _state.value = LoginState.Error(throwable)
    }

    fun clear() {
        _state.value = LoginState.Clear
    }

    fun signIn(name: String, password: String) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = LoginState.Loading
            withContext(Dispatchers.IO) {
                postLoginAuthUseCase.invoke(name, password).onSuccess {
                    deleteAllLoansUseCase.invoke()
                    withContext(Dispatchers.Main) {
                        authTokenManager.saveAuthToken(it)
                        authInterceptor.updateToken(it)
                        _state.value = LoginState.Success
                    }
                }.onFailure { throwable ->
                    withContext(Dispatchers.Main) {
                        val customException =
                            if (throwable is DomainException.NotFoundException) Exception(
                                application.applicationContext.getString(
                                    R.string.wrong_authentication
                                )
                            ) else throwable
                        _state.value = LoginState.Error(customException)
                    }
                }
            }
        }
    }

    fun checkHasTokenInSharedPrefs() {
        if (authTokenManager.getAuthToken()?.isNotEmpty() == true) {
            val token = authTokenManager.getAuthToken() ?: ""
            authInterceptor.updateToken(token)
            _state.value = LoginState.Success
        }
    }

    fun validateLogin(name: String): Boolean = when {
        name.isEmpty() -> false
        Pattern.compile("^[A-ZА-ЯЁa-z,а-я-'ё]{0,20}$").matcher(name).find() -> true
        else -> false
    }

    fun validatePassword(password: String): Boolean = when {
        password.isEmpty() -> false
        password.isNotEmpty() -> true
        else -> false
    }
}