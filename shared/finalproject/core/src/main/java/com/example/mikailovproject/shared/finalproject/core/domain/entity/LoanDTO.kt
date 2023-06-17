package com.example.mikailovproject.shared.finalproject.core.domain.entity

data class LoanDTO(
    val amount: Double,
    val date: String,
    val firstName: String,
    val id: Long,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: String,
)