package com.example.caiomansho.domain


import com.example.caiomansho.data.repository.WalletRepository
import com.example.caiomansho.domain.exception.NoBalanceException
import com.example.caiomansho.domain.exception.TransferFailedException
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransferUseCaseImpl @Inject constructor(
    private val walletRepository: WalletRepository
): TransferUseCase {

    override operator fun invoke(value: Float): Flow<Boolean> = flow {
        val balance = walletRepository.getBalance()
        if(value > balance){
            throw NoBalanceException()
        } else if(value == 403.0f) {
            throw TransferFailedException()
        }
        walletRepository.transfer(value)
        emit(true)
    }



}