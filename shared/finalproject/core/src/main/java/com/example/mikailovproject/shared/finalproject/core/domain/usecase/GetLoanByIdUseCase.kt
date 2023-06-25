package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import javax.inject.Inject

class GetLoanByIdUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke(id: String): Result<LoanDTO> {
        return repository.getLoanById(id)
    }
}