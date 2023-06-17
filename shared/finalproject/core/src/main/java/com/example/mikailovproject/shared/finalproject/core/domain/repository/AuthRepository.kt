package com.example.mikailovproject.shared.finalproject.core.domain.repository

import com.example.mikailovproject.shared.finalproject.core.domain.entity.AuthDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.UserEntityDTO

interface AuthRepository {

    suspend fun login(user: AuthDTO): Result<String>

    suspend fun registration(user: AuthDTO): Result<UserEntityDTO>
}