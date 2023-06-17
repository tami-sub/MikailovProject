package com.example.mikailovproject.shared.finalproject.core.data.repository

import com.example.mikailovproject.shared.finalproject.core.data.remote.LoanApi
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanConditionsDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanRequestDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import javax.inject.Inject
import javax.inject.Named

class LoanRepositoryImpl @Inject constructor(@Named("AUTH_USER") private val loanApi: LoanApi) :
    LoanRepository {

    override suspend fun postCreateLoan(loan: LoanRequestDTO): Result<LoanDTO> {
        return loanApi.postCreateLoan(loan)
    }

    override suspend fun getLoanById(id: Long): Result<LoanDTO> {
        return loanApi.getLoanById(id)
    }

    override suspend fun getAllLoans(): Result<List<LoanDTO>> {
        return loanApi.getAllLoans()
    }

    override suspend fun getLoanConditions(): Result<LoanConditionsDTO> {
        return loanApi.getLoanConditions()
    }
}