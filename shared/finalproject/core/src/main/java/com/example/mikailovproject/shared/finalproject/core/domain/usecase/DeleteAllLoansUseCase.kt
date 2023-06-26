package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import javax.inject.Inject

class DeleteAllLoansUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke() {
        return repository.deleteAllLoansFromDatabase()
    }
}