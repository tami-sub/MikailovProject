package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanConditionsDTO
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
class GetLoanConditionsUseCaseTest {
    @Mock
    private lateinit var mockRepository: LoanRepository

    private lateinit var useCase: GetLoanConditionsUseCase

    @Before
    fun setup() {
        useCase = GetLoanConditionsUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success result`() = runBlocking {
        val expectedLoanConditions = LoanConditionsDTO(10000, 15.0, 6)

        `when`(mockRepository.getLoanConditions()).thenReturn(Result.success(expectedLoanConditions))

        val result = useCase.invoke()

        assertEquals(Result.success(expectedLoanConditions), result)
    }

    @Test
    fun `invoke should return failure result`() = runBlocking {
        val errorMessage = "Failed to retrieve loan conditions"

        `when`(mockRepository.getLoanConditions()).thenReturn(Result.failure(Exception(errorMessage)))

        val result = useCase.invoke()

        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}