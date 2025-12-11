package com.example.caiomansho.domain

import android.content.Context
import com.example.caiomansho.R
import com.example.caiomansho.data.repository.WalletRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransferUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val walletRepository: WalletRepository
): TransferUseCase {

    override operator fun invoke(value: Float): Flow<Boolean> = flow {
        val balance = walletRepository.getBalance()
        if(value > balance){
            throw Exception(context.getString(R.string.no_balance))
        }
        if(value in 403.0..<404.0) {
            throw Exception(context.getString(R.string.failed_transfer))
        }
        walletRepository.transfer(value)
        emit(true)
    }



}