package com.example.mikailovproject.shared.finalproject.core.data.datasource

import javax.inject.Inject

class RandomFactsLocalDataSource @Inject constructor() : RandomFactsDataSource {

    private val mockedCache = "String from local data source"

    override fun get(): String = mockedCache
}