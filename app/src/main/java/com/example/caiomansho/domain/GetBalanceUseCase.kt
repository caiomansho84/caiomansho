package com.example.caiomansho.domain

import kotlinx.coroutines.flow.Flow

interface GetBalanceUseCase {
    operator fun invoke(): Flow<Float>
}