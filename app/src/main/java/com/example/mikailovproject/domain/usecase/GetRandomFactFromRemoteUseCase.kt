package com.example.mikailovproject.domain.usecase

import com.example.mikailovproject.data.repository.RandomFactsRepositoryImpl
import com.example.mikailovproject.domain.repository.RandomFactsRepository

class GetRandomFactFromRemoteUseCase {

    //TODO: DI
    //TODO: сделать так, чтобы repository не пересоздавался для каждого UseCase
    private val repository: RandomFactsRepository = RandomFactsRepositoryImpl()

    operator fun invoke(): String {
        val fromRemote = repository.getFromRemote()
        val repoInstanceHash = repository.hashCode()

        return "$fromRemote, repo hash = $repoInstanceHash"
    }
}