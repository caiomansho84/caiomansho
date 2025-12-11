package com.example.caiomansho

import com.example.caiomansho.data.repository.WalletRepository
import com.example.caiomansho.domain.TransferUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class TransferUseCaseImplTest {

    private lateinit var walletRepository: WalletRepository
    private lateinit var useCase: TransferUseCaseImpl

    @Before
    fun setup() {
        walletRepository = mock()
        useCase = TransferUseCaseImpl(walletRepository)
    }

    @Test
    fun shouldThrowExceptionWhenBalanceIsInsufficient() = runTest(UnconfinedTestDispatcher()) {
        // Arrange
        whenever(walletRepository.getBalance()).thenReturn(100f)

        // Act
        val result = runCatching { useCase.invoke(200f).first() }

        // Assert
        assertTrue(result.exceptionOrNull()?.message!!.contains("Saldo insuficiente"))
        verify(walletRepository, never()).transfer(any())
    }

    @Test
    fun shouldThrowExceptionWhenValueIsBetween403And404() = runTest(UnconfinedTestDispatcher()) {
        // Arrange
        whenever(walletRepository.getBalance()).thenReturn(1000f)

        // Act
        val result = runCatching { useCase.invoke(403.5f).first() }

        // Assert
        assertTrue(result.exceptionOrNull()?.message!!.contains("Falha ao transferir"))
        verify(walletRepository, never()).transfer(any())
    }

    @Test
    fun shouldTransferSuccessfullyWhenValueIsValid() = runTest(UnconfinedTestDispatcher()) {
        // Arrange
        whenever(walletRepository.getBalance()).thenReturn(1000f)

        // Act
        val result = useCase.invoke(100f).first()

        // Assert
        assertTrue(result)
        verify(walletRepository).transfer(100f)
    }
}
