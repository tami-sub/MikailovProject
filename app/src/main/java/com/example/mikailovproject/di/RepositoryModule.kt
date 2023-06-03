package com.example.mikailovproject.di

import com.example.mikailovproject.shared.randomfact.core.domain.repository.RemoteRepository
import com.example.mikailovproject.shared.randomfact.core.data.repository.RemoteRepositoryImpl
import com.example.mikailovproject.shared.randomfact.core.data.repository.RandomFactsRepositoryImpl
import com.example.mikailovproject.shared.randomfact.core.domain.repository.RandomFactsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindRandomFactsRepository(impl: RandomFactsRepositoryImpl): RandomFactsRepository

    @Binds
    fun bindRemoteRepository(impl: RemoteRepositoryImpl): RemoteRepository
}