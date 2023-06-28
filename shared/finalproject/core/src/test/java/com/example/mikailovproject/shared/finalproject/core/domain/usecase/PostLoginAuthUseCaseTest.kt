package com.example.mikailovproject.shared.finalproject.core.domain.usecase

import com.example.mikailovproject.shared.finalproject.core.domain.entity.AuthDTO
import com.example.mikailovproject.shared.finalproject.core.domain.entity.Role
import com.example.mikailovproject.shared.finalproject.core.domain.entity.UserEntityDTO
import com.example.mikailovproject.shared.finalproject.core.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostLoginAuthUseCaseTest {
    @Mock
    private lateinit var mockRepository: AuthRepository

    private lateinit var useCase: PostLoginAuthUseCase
    private val name = "Oo"
    private val password = "Oo1!"

    @Before
    fun setup() {
        useCase = PostLoginAuthUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success result`() = runBlocking {
        val expectedToken = "example_token"

        `when`(mockRepository.login(AuthDTO(name, password))).thenReturn(
            Result.success(expectedToken)
        )

        val result = useCase.invoke(name, password)

        assertEquals(Result.success(expectedToken), result)
    }

    @Test
    fun `invoke should return failure result`() = runBlocking {
        val errorMessage = "Login failed"

        `when`(mockRepository.login(AuthDTO(name, password))).thenReturn(
            Result.failure(Throwable(errorMessage))
        )

        val result = useCase.invoke(name, password)

        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}