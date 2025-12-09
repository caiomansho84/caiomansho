package com.example.caiomansho.domain

import kotlinx.coroutines.flow.Flow

interface TransferUseCase {
    operator fun invoke(value: Float): Flow<Boolean>
}