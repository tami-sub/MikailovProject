package com.example.mikailovproject.shared.randomfact.core.domain.usecase

import com.example.mikailovproject.shared.randomfact.core.domain.entity.SuccessDTO
import com.example.mikailovproject.shared.randomfact.core.domain.repository.RandomFactsRepository
import com.example.mikailovproject.shared.randomfact.core.domain.repository.RemoteRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class GetRemotePostFileUseCase @Inject constructor(private val repository: RemoteRepository) {

    suspend operator fun invoke(file: MultipartBody.Part): Result<SuccessDTO> {
        return repository.uploadFile(file)
    }
}