package com.example.mikailovproject.domain.di

import com.example.mikailovproject.data.repository.RandomFactsRepositoryImpl
import com.example.mikailovproject.di.AppScope
import com.example.mikailovproject.domain.repository.RandomFactsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @AppScope
    @Binds
    fun bindRandomFactsRepository(impl: RandomFactsRepositoryImpl): RandomFactsRepository
}