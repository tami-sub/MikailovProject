package com.example.mikailovproject.domain.di

import com.example.mikailovproject.data.repository.RandomFactsRepositoryImpl
import com.example.mikailovproject.di.AppScope
import com.example.mikailovproject.di.activity.ActivityScope
import com.example.mikailovproject.di.fragment.FragmentScope
import com.example.mikailovproject.domain.repository.RandomFactsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @FragmentScope
    @Binds
    fun bindRandomFactsRepository(impl: RandomFactsRepositoryImpl): RandomFactsRepository
}