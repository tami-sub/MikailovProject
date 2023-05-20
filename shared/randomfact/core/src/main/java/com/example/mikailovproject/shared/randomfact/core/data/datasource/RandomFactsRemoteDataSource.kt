package com.example.mikailovproject.shared.randomfact.core.data.datasource

import javax.inject.Inject

class RandomFactsRemoteDataSource @Inject constructor() : RandomFactsDataSource {

    private val mockedAnswer = "String from remote data source"
    override fun get(): String = mockedAnswer
}