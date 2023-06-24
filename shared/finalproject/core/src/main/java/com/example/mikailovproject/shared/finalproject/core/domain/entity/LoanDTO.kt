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
) {
    fun toEntity(): LoanEntity {
        return LoanEntity(
            id = id,
            amount = amount,
            date = date,
            firstName = firstName,
            lastName = lastName,
            percent = percent,
            period = period,
            phoneNumber = phoneNumber,
            state = state
        )
    }
}