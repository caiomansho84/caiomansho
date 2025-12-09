package com.example.caiomansho.domain

import com.example.caiomansho.data.repository.UserRepository
import com.example.caiomansho.data.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBalanceUseCaseImpl(
    private val walletRepository: WalletRepository
): GetBalanceUseCase {

    override fun invoke(): Flow<Float>  = flow {
        emit(walletRepository.getBalance())
    }

}