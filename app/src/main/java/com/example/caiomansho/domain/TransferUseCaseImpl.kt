package com.example.caiomansho.domain

import com.example.caiomansho.data.repository.UserRepository
import com.example.caiomansho.data.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransferUseCaseImpl(
    private val walletRepository: WalletRepository
): TransferUseCase {

    override operator fun invoke(value: Float): Flow<Boolean> = flow {
        walletRepository.transfer(value)
        emit(true)
    }

}