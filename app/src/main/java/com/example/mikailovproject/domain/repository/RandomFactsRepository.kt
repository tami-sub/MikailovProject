package com.example.mikailovproject.domain.repository

interface RandomFactsRepository {

    fun getFromRemote(): String

    fun getFromLocal(): String
}