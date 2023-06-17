package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanRequestDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import javax.inject.Inject

class PostCreateLoanUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke(
        amount: Long,
        firstName: String,
        lastName: String,
        percent: Double,
        period: Int,
        phoneNumber: String,
    ): Result<LoanDTO> {
        return repository.postCreateLoan(
            LoanRequestDTO(
                amount, firstName, lastName, percent, period, phoneNumber
            )
        )
    }
}