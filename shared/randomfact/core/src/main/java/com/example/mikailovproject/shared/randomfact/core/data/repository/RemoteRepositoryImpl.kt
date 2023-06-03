package com.example.mikailovproject.shared.randomfact.core.data.repository

import com.example.mikailovproject.shared.randomfact.core.data.remote.RemoteApi
import com.example.mikailovproject.shared.randomfact.core.domain.entity.SuccessDTO
import com.example.mikailovproject.shared.randomfact.core.domain.repository.RemoteRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val remoteApi: RemoteApi) :
    RemoteRepository {
    override suspend fun uploadFile(
        file: MultipartBody.Part,
    ): Result<SuccessDTO> {
        return remoteApi.uploadFile(file)
    }
}