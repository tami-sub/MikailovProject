package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanRequestDTO
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
class PostCreateLoanUseCaseTest {
    @Mock
    private lateinit var mockRepository: LoanRepository

    private lateinit var useCase: PostCreateLoanUseCase
    private val amount = 13000.0
    private val firstName = "Joka"
    private val lastName = "Boka"
    private val percent = 5.0
    private val period = 12
    private val phoneNumber = "88005553535"

    @Before
    fun setup() {
        useCase = PostCreateLoanUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success result`() = runBlocking {
        val expectedLoanEntity = LoanDTO(
            amount, "2023-06-28", firstName, 1L, lastName, percent, period, phoneNumber, "Active"
        )

        val loanRequest = LoanRequestDTO(amount, firstName, lastName, percent, period, phoneNumber)

        `when`(mockRepository.postCreateLoan(loanRequest)).thenReturn(Result.success(expectedLoanEntity))

        val result = useCase.invoke(amount, firstName, lastName, percent, period, phoneNumber)

        assertEquals(Result.success(expectedLoanEntity), result)
    }

    @Test
    fun `invoke should return failure result`() = runBlocking {
        val errorMessage = "Failed to create loan"

        val loanRequest = LoanRequestDTO(amount, firstName, lastName, percent, period, phoneNumber)

        `when`(mockRepository.postCreateLoan(loanRequest)).thenReturn(Result.failure(Throwable(errorMessage)))

        val result = useCase.invoke(amount, firstName, lastName, percent, period, phoneNumber)

        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}