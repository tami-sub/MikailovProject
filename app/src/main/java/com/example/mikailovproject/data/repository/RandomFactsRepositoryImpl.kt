package com.example.mikailovproject.data.repository

import com.example.mikailovproject.data.datasource.RandomFactsDataSource
import com.example.mikailovproject.data.datasource.RandomFactsLocalDataSource
import com.example.mikailovproject.data.datasource.RandomFactsRemoteDataSource
import com.example.mikailovproject.domain.repository.RandomFactsRepository
import javax.inject.Inject
import javax.inject.Named

class RandomFactsRepositoryImpl @Inject constructor(
    @Named("local") private val localDataSource: RandomFactsDataSource,
    @Named("remote") private val remoteDataSource: RandomFactsDataSource,
) : RandomFactsRepository {

    override fun getFromRemote(): String =
        remoteDataSource.get()

    override fun getFromLocal(): String =
        localDataSource.get()
}