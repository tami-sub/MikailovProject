package com.example.mikailovproject.domain.usecase

import com.example.mikailovproject.domain.repository.RandomFactsRepository
import javax.inject.Inject

class GetRandomFactFromRemoteUseCase @Inject constructor(private val repository: RandomFactsRepository) {

    operator fun invoke(): String {
        val fromRemote = repository.getFromRemote()
        val repoInstanceHash = repository.hashCode()

        return "$fromRemote, repo hash = $repoInstanceHash"
    }
}