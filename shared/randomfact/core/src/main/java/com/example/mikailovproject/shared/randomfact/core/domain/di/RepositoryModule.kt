package com.example.mikailovproject.shared.randomfact.core.domain.di

import com.example.mikailovproject.shared.randomfact.core.data.repository.RandomFactsRepositoryImpl
import com.example.mikailovproject.shared.randomfact.core.domain.repository.RandomFactsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindRandomFactsRepository(impl: RandomFactsRepositoryImpl): RandomFactsRepository
}