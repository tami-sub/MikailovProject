package com.example.mikailovproject.di

import com.example.mikailovproject.shared.finalproject.core.data.repository.AuthRepositoryImpl
import com.example.mikailovproject.shared.finalproject.core.data.repository.LoanRepositoryImpl
import com.example.mikailovproject.shared.finalproject.core.data.repository.RandomFactsRepositoryImpl
import com.example.mikailovproject.shared.finalproject.core.domain.repository.AuthRepository
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import com.example.mikailovproject.shared.finalproject.core.domain.repository.RandomFactsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindRandomFactsRepository(impl: RandomFactsRepositoryImpl): RandomFactsRepository

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindLoanRepository(impl: LoanRepositoryImpl): LoanRepository
}