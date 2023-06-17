package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.repository.RandomFactsRepository
import javax.inject.Inject

class GetRandomFactFromLocalUseCase @Inject constructor(private val repository: RandomFactsRepository) {

    operator fun invoke(): String {
        val fromLocal = repository.getFromLocal()
        val repoInstanceHash = repository.hashCode()

        return "$fromLocal, repo hash = $repoInstanceHash"
    }
}