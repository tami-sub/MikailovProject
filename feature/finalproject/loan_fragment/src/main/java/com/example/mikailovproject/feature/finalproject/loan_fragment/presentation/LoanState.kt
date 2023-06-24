package com.example.mikailovproject.feature.finalproject.loan_fragment.presentation

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO

sealed class LoanState {
    object Loading : LoanState()
    data class SuccessCreation(val loan: LoanDTO) : LoanState()
    data class SuccessConditions(
        val maxAmount: Long, val percent: Double, val period: Int
    ) : LoanState()

    data class Error(val exception: Throwable, val isConditions: Boolean) : LoanState()
    object Clear : LoanState()
}