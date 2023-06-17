package com.example.mikailovproject.shared.finalproject.core.data.remote

import com.example.mikailovproject.shared.finalproject.core.domain.entity.AuthDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.UserEntityDTO
import retrofit2.http.*

interface AuthApi {

    @POST("login")
    suspend fun login(@Body user: AuthDTO): Result<String>

    @POST("registration")
    suspend fun registration(@Body user: AuthDTO): Result<UserEntityDTO>
}