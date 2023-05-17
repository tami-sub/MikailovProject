package com.example.mikailovproject.domain.usecase

import com.example.mikailovproject.data.repository.RandomFactsRepositoryImpl
import com.example.mikailovproject.domain.repository.RandomFactsRepository

class GetRandomFactFromLocalUseCase {

    //TODO: DI
    //TODO: сделать так, чтобы repository не пересоздавался для каждого UseCase
    private val repository: RandomFactsRepository = RandomFactsRepositoryImpl()

    operator fun invoke(): String {
        val fromLocal = repository.getFromLocal()
        val repoInstanceHash = repository.hashCode()

        return "$fromLocal, repo hash = $repoInstanceHash"
    }
}