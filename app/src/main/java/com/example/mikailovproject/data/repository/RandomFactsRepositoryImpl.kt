package com.example.mikailovproject.data.repository

import com.example.mikailovproject.data.datasource.RandomFactsDataSource
import com.example.mikailovproject.data.datasource.RandomFactsLocalDataSource
import com.example.mikailovproject.data.datasource.RandomFactsRemoteDataSource
import com.example.mikailovproject.domain.repository.RandomFactsRepository

class RandomFactsRepositoryImpl : RandomFactsRepository {

    //TODO: DI
    private val localDataSource: RandomFactsDataSource = RandomFactsLocalDataSource()
    private val remoteDataSource: RandomFactsDataSource = RandomFactsRemoteDataSource()

    override fun getFromRemote(): String =
        remoteDataSource.get()

    override fun getFromLocal(): String =
        localDataSource.get()
}