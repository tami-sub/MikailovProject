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
class PostRegistrationAuthUseCaseTest {
    @Mock
    private lateinit var mockRepository: AuthRepository

    private lateinit var useCase: PostRegistrationAuthUseCase
    private val name = "Oo"
    private val password = "Oo1!"

    @Before
    fun setup() {
        useCase = PostRegistrationAuthUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success result`() = runBlocking {

        val expectedUserEntity = UserEntityDTO(name, Role.USER)

        `when`(mockRepository.registration(AuthDTO(name, password))).thenReturn(
            Result.success(
                expectedUserEntity
            )
        )

        val result = useCase.invoke(name, password)

        assertEquals(Result.success(expectedUserEntity), result)
    }

    @Test
    fun `invoke should return failure result`() = runBlocking {

        val errorMessage = "Registration failed"

        `when`(
            mockRepository.registration(
                AuthDTO(
                    name, password
                )
            )
        ).thenReturn(Result.failure(Throwable(errorMessage)))


        val result = useCase.invoke(name, password)

        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}