package com.example.mikailovproject.shared.finalproject.core.data.repository

import com.example.mikailovproject.shared.finalproject.core.data.datasource.RandomFactsDataSource
import com.example.mikailovproject.shared.finalproject.core.domain.repository.RandomFactsRepository
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