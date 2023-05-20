package com.example.mikailovproject.di

import com.example.mikailovproject.di.fragment.FragmentScope
import com.example.mikailovproject.shared.randomfact.core.data.repository.RandomFactsRepositoryImpl
import com.example.mikailovproject.shared.randomfact.core.domain.repository.RandomFactsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @FragmentScope
    @Binds
    fun bindRandomFactsRepository(impl: RandomFactsRepositoryImpl): RandomFactsRepository
}