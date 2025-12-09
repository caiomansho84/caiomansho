package com.example.caiomansho.domain

import kotlinx.coroutines.flow.Flow

interface GetLoggedUseCase {
    operator fun invoke(): Flow<String>
}