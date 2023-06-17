package com.example.mikailovproject.feature.finalproject.mainFragment.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikailovproject.network.retrofit.AuthInterceptor
import com.example.mikailovproject.shared.finalproject.core.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRandomFactFromLocalUseCase: GetRandomFactFromLocalUseCase,
    private val getRandomFactFromRemoteUseCase: GetRandomFactFromRemoteUseCase,
    private val loginAuthUseCase: PostLoginAuthUseCase,
    private val registrationAuthUseCase: PostRegistrationAuthUseCase,
    private val createLoanUseCase: PostCreateLoanUseCase,
    private val getLoanByIdUseCase: GetLoanByIdUseCase,
    private val getAllLoans: GetAllLoansUseCase,
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val application: Application,
    private val authInterceptor: AuthInterceptor,
) : ViewModel() {

    private val _state: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    private var _postedFile: MutableLiveData<String?> = MutableLiveData<String?>()
    val postedFile: LiveData<String?> = _postedFile

    private var percent = 0.0
    private var period = 0

    init {
        login("joka", "boka")
        Log.d("jokahash", "MainViewModel ${this.hashCode()}")
    }

    fun loadStrings() {
        _state.value = MainState.Loading

        val fromLocal = getRandomFactFromLocalUseCase.invoke()
        val fromRemote = getRandomFactFromRemoteUseCase.invoke()

        _state.value = MainState.Success(remoteString = fromRemote, localString = fromLocal)
    }

    private fun login(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginAuthUseCase.invoke(name, password)
                .onSuccess {
                    Log.d("joka", it.toString())
                    val token = it
                    authInterceptor.updateToken(token) // Update the token in the AuthInterceptor
                    getLoanConditions()
                    getAllLoans()
                    getLoanById(81)
                }.onFailure {
                    Log.d("joka", it.toString())
                }
        }
    }

    private suspend fun getLoanConditions() {
        getLoanConditionsUseCase.invoke().onSuccess {
            Log.d("joka", it.toString())
            percent = it.percent
            period = it.period
        }.onFailure {
            Log.d("joka", "IN INTERCEPTER $it")
        }
    }

    private suspend fun getLoanById(id: Long) {
        getLoanByIdUseCase.invoke(id).onSuccess {
            Log.d("joka", it.toString())
        }.onFailure {
            Log.d("joka", it.toString())
        }
    }

    private suspend fun getAllLoans() {
        getAllLoans.invoke().onSuccess {
            Log.d("joka", it.toString())
        }.onFailure {
            Log.d("joka", it.toString())
        }
    }

    fun createFile() {
        viewModelScope.launch(Dispatchers.IO) {
            createLoanUseCase.invoke(45, "Joka", "Boka", percent, period, "88005553535").onSuccess {
                Log.d("joka", it.toString())
            }.onFailure {
                Log.d("joka", it.toString())
            }
//            registrationAuthUseCase.invoke("jokler", "bokler").onSuccess {
//                Log.d("joka",it.toString())
//            }.onFailure {
//                Log.d("joka",it.toString())
//            }
        }
    }
}
