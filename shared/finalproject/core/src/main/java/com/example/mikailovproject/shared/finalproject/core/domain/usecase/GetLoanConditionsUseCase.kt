package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanConditionsDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import javax.inject.Inject

class GetLoanConditionsUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke(): Result<LoanConditionsDTO> {
        return repository.getLoanConditions()
    }
}