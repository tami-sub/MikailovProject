package com.example.mikailovproject.shared.finalproject.core.data.repository

import com.example.mikailovproject.shared.finalproject.core.data.remote.AuthApi
import com.example.mikailovproject.shared.finalproject.core.domain.entity.AuthDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.UserEntityDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {

    override suspend fun login(
        user: AuthDTO,
    ): Result<String> {
        return authApi.login(user)
    }

    override suspend fun registration(
        user: AuthDTO,
    ): Result<UserEntityDTO> {
        return authApi.registration(user)
    }
}