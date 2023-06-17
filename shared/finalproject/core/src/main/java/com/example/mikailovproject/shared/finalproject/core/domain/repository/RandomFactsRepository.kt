package com.example.mikailovproject.shared.finalproject.core.domain.repository

interface RandomFactsRepository {

    fun getFromRemote(): String

    fun getFromLocal(): String
}