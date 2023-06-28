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
class GetAllLoansUseCaseTest {
    @Mock
    private lateinit var mockRepository: LoanRepository

    private lateinit var useCase: GetAllLoansUseCase

    @Before
    fun setup() {
        useCase = GetAllLoansUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success result with loan list`() = runBlocking {
        val expectedLoanDTOList = listOf(
            LoanDTO(
                amount = 1000.0,
                date = "2023-06-28",
                firstName = "Garry",
                id = 12,
                lastName = "Stiew",
                percent = 5.0,
                period = 12,
                phoneNumber = "1234567890",
                state = "Approved"
            ),
            LoanDTO(
                amount = 2000.0,
                date = "2023-06-29",
                firstName = "Doctor",
                id = 11,
                lastName = "Who",
                percent = 7.5,
                period = 24,
                phoneNumber = "555343445",
                state = "Rejected"
            )
        )

        val expectedLoanEntityList = expectedLoanDTOList.map { it.toEntity() }

        `when`(mockRepository.getAllLoans()).thenReturn(Result.success(expectedLoanDTOList))
        `when`(mockRepository.getAllLoansFromDatabase()).thenReturn(expectedLoanEntityList)

        val (resultDTO, resultEntity) = useCase.invoke()

        assertEquals(Result.success(expectedLoanDTOList), resultDTO)
        assertEquals(expectedLoanEntityList, resultEntity)
    }

    @Test
    fun `invoke should return failure result`() = runBlocking {
        val errorMessage = "Failed to fetch loans"

        `when`(mockRepository.getAllLoans()).thenReturn(Result.failure(Throwable(errorMessage)))

        val (resultDTO, resultEntity) = useCase.invoke()

        assertEquals(errorMessage, resultDTO.exceptionOrNull()?.message)
    }
}