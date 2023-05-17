package com.example.mikailovproject.data.datasource

import com.example.mikailovproject.data.datasource.RandomFactsDataSource

class RandomFactsLocalDataSource : RandomFactsDataSource {

    private val mockedCache = "String from local data source"

    override fun get(): String = mockedCache
}