package com.example.caiomansho.domain


import com.example.caiomansho.data.repository.WalletRepository
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
        }
        if(value == 403.0f) {
            throw TransferFailedException()
        }
        walletRepository.transfer(value)
        emit(true)
    }



}