package com.example.mikailovproject.shared.finalproject.core.domain.entity

data class LoanConditionsDTO(
    var maxAmount: Long,
    var percent: Double,
    var period: Int
)