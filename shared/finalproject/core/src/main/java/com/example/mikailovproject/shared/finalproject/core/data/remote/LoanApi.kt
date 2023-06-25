package com.example.mikailovproject.shared.finalproject.core.data.remote

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanConditionsDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanRequestDTO
import retrofit2.http.*

interface LoanApi {

    @POST("loans")
    suspend fun postCreateLoan(@Body loan: LoanRequestDTO): Result<LoanDTO>

    @GET("loans/{id}")
    suspend fun getLoanById(
        @Path("id") id: String,
    ): Result<LoanDTO>

    @GET("loans/all")
    suspend fun getAllLoans(): Result<List<LoanDTO>>

    @GET("loans/conditions")
    suspend fun getLoanConditions(): Result<LoanConditionsDTO>
}