package com.example.mikailovproject.shared.randomfact.core.domain.repository

interface RandomFactsRepository {

    fun getFromRemote(): String

    fun getFromLocal(): String
}