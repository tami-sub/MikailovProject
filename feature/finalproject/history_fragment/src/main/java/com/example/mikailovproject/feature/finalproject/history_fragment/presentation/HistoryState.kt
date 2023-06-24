package com.example.mikailovproject.feature.finalproject.history_fragment.presentation

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity

sealed class HistoryState {
    object Loading : HistoryState()
    data class Success(val allLoans: List<LoanEntity>) : HistoryState()
    data class Error(val exception: Throwable, val allLoans: List<LoanEntity>) : HistoryState()
    object Clear : HistoryState()
}