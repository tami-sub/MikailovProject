package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.AuthDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.AuthRepository
import javax.inject.Inject

class PostLoginAuthUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(name: String, password: String): Result<String> {
        return repository.login(AuthDTO(name, password))
    }
}