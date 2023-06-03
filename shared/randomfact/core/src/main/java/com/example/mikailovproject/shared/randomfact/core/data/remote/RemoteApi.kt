package com.example.mikailovproject.shared.randomfact.core.data.remote

import com.example.mikailovproject.shared.randomfact.core.domain.entity.SuccessDTO
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RemoteApi {
    @Multipart
    @POST("upload")
    suspend fun uploadFile(
        @Part file: MultipartBody.Part
    ): Result<SuccessDTO>
}