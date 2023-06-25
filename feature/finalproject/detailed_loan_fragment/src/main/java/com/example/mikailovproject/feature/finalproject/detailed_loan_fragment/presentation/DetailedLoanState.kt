package com.example.mikailovproject.feature.finalproject.detailed_loan_fragment.presentation

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO

sealed class DetailedLoanState {
    object Loading : DetailedLoanState()
    data class Success(val loan: LoanDTO) : DetailedLoanState()
    data class Error(val exception: Throwable) : DetailedLoanState()
    object Clear : DetailedLoanState()
}