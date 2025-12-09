package com.example.caiomansho.domain

import kotlinx.coroutines.flow.Flow

interface IsLoggedUseCase {
    operator fun invoke(): Flow<Boolean>
}