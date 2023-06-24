package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import javax.inject.Inject

class GetAllLoansUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke(): Pair<Result<List<LoanDTO>>, List<LoanEntity>> {
        return Pair(repository.getAllLoans(), repository.getAllLoansFromDatabase())
    }
}