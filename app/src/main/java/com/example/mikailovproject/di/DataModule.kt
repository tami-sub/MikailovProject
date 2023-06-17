package com.example.mikailovproject.di

import com.example.mikailovproject.shared.finalproject.core.data.datasource.RandomFactsDataSource
import com.example.mikailovproject.shared.finalproject.core.data.datasource.RandomFactsLocalDataSource
import com.example.mikailovproject.shared.finalproject.core.data.datasource.RandomFactsRemoteDataSource
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