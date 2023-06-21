package com.example.mikailovproject.di

import com.example.mikailovproject.shared.finalproject.core.data.repository.AuthRepositoryImpl
import com.example.mikailovproject.shared.finalproject.core.data.repository.LoanRepositoryImpl
import com.example.mikailovproject.shared.finalproject.core.domain.repository.AuthRepository
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindLoanRepository(impl: LoanRepositoryImpl): LoanRepository
}