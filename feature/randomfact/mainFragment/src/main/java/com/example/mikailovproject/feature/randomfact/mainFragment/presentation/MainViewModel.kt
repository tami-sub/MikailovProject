package com.example.mikailovproject.feature.randomfact.mainFragment.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikailovproject.shared.randomfact.core.domain.entity.SuccessDTO
import com.example.mikailovproject.shared.randomfact.core.domain.usecase.GetRandomFactFromLocalUseCase
import com.example.mikailovproject.shared.randomfact.core.domain.usecase.GetRandomFactFromRemoteUseCase
import com.example.mikailovproject.shared.randomfact.core.domain.usecase.GetRemotePostFileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRandomFactFromLocalUseCase: GetRandomFactFromLocalUseCase,
    private val getRandomFactFromRemoteUseCase: GetRandomFactFromRemoteUseCase,
    private val postRemoteFileUseCase: GetRemotePostFileUseCase,
    private val application: Application,
) : ViewModel() {

    private val _state: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    private var _postedFile: MutableLiveData<SuccessDTO?> = MutableLiveData<SuccessDTO?>()
    val postedFile: LiveData<SuccessDTO?> = _postedFile

    fun loadStrings() {
        _state.value = MainState.Loading

        val fromLocal = getRandomFactFromLocalUseCase.invoke()
        val fromRemote = getRandomFactFromRemoteUseCase.invoke()

        _state.value = MainState.Success(remoteString = fromRemote, localString = fromLocal)
    }

    fun createFile() {
        val fileName = "JokaFile.txt"
        val file = File(application.filesDir, fileName)
        try {
            file.createNewFile()
            val content = "Ahahahhahahahhahhah"
            file.writeText(content)
            val requestFile: RequestBody =
                file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val filePart: MultipartBody.Part =
                MultipartBody.Part.createFormData("file", file.name, requestFile)

            viewModelScope.launch {
                uploadFile(filePart)
            }

        } catch (e: IOException) {
            _postedFile.value = null
        }

    }

    private suspend fun uploadFile(filePart: MultipartBody.Part) {
        postRemoteFileUseCase.invoke(filePart).onSuccess {
            _postedFile.value = it
        }.onFailure {
            _postedFile.value = null
        }
    }
}