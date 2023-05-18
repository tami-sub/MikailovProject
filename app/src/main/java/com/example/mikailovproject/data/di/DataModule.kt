package com.example.mikailovproject.data.di

import com.example.mikailovproject.data.datasource.RandomFactsDataSource
import com.example.mikailovproject.data.datasource.RandomFactsLocalDataSource
import com.example.mikailovproject.data.datasource.RandomFactsRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Named


@Module
interface DataModule {
    @Binds
    @Named("local")
    fun bindLocalRandomFactsDataSource(randomFactsLocalDataSource: RandomFactsLocalDataSource): RandomFactsDataSource

    @Binds
    @Named("remote")
    fun bindRemoteRandomFactsDataSource(randomFactsRemoteDataSource: RandomFactsRemoteDataSource): RandomFactsDataSource
}