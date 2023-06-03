package com.example.mikailovproject.shared.randomfact.core.domain.repository

import com.example.mikailovproject.shared.randomfact.core.domain.entity.SuccessDTO
import okhttp3.MultipartBody

interface RemoteRepository {
    suspend fun uploadFile(file: MultipartBody.Part): Result<SuccessDTO>
}