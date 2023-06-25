package com.example.mikailovproject.shared.finalproject.core.data.repository

import com.example.mikailovproject.shared.finalproject.core.data.local.LoanDao
import com.example.mikailovproject.shared.finalproject.core.data.remote.LoanApi
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanConditionsDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanRequestDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import javax.inject.Inject
import javax.inject.Named

class LoanRepositoryImpl @Inject constructor(
    @Named("AUTH_USER") private val loanApi: LoanApi, private val loanDao: LoanDao
) : LoanRepository {

    override suspend fun postCreateLoan(loan: LoanRequestDTO): Result<LoanDTO> {
        return loanApi.postCreateLoan(loan)
    }

    override suspend fun getLoanById(id: String): Result<LoanDTO> {
        return loanApi.getLoanById(id)
    }

    override suspend fun getAllLoans(): Result<List<LoanDTO>> {
        val data = loanApi.getAllLoans()
        data.onSuccess {
            loanDao.insertLoans(it.map { x -> x.toEntity() })
        }
        return data
    }

    override suspend fun getLoanConditions(): Result<LoanConditionsDTO> {
        return loanApi.getLoanConditions()
    }

    override suspend fun getAllLoansFromDatabase(): List<LoanEntity> {
        return loanDao.getAllLoans()
    }
}