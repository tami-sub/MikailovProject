package com.example.mikailovproject.shared.finalproject.core.domain.entity

data class LoanRequestDTO(
    val amount: Long,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
)
