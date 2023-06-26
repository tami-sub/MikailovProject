package com.example.mikailovproject.shared.finalproject.core.domain.repository

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanConditionsDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanRequestDTO

interface LoanRepository {

    suspend fun getLoanConditions(): Result<LoanConditionsDTO>

    suspend fun getLoanById(id: String): Result<LoanDTO>

    suspend fun getAllLoans(): Result<List<LoanDTO>>

    suspend fun postCreateLoan(loan: LoanRequestDTO): Result<LoanDTO>

    suspend fun getAllLoansFromDatabase(): List<LoanEntity>

    suspend fun deleteAllLoansFromDatabase()
}