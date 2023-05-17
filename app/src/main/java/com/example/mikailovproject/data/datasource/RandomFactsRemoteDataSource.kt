package com.example.mikailovproject.data.datasource

import com.example.mikailovproject.data.datasource.RandomFactsDataSource

class RandomFactsRemoteDataSource : RandomFactsDataSource {

    private val mockedAnswer = "String from remote data source"

    override fun get(): String = mockedAnswer
}