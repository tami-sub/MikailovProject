package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.LoanRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetLoanByIdUseCaseTest {
    @Mock
    private lateinit var mockRepository: LoanRepository

    private lateinit var useCase: GetLoanByIdUseCase
    private val loanId = "23"

    @Before
    fun setup() {
        useCase = GetLoanByIdUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success result`() = runBlocking {
        val expectedLoanDTO = LoanDTO(
            amount = 1000.0,
            date = "28-06-2023",
            firstName = "Oukey",
            id = 1234,
            lastName = "Oo",
            percent = 5.0,
            period = 12,
            phoneNumber = "88005553535",
            state = "active"
        )

        `when`(mockRepository.getLoanById(loanId)).thenReturn(Result.success(expectedLoanDTO))

        val result = useCase.invoke(loanId)

        assertEquals(Result.success(expectedLoanDTO), result)
    }

    @Test
    fun `invoke should return failure result`() = runBlocking {
        val errorMessage = "Failed to retrieve loan"

        `when`(mockRepository.getLoanById(loanId)).thenReturn(Result.failure(Throwable(errorMessage)))

        val result = useCase.invoke(loanId)

        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}