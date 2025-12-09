package com.example.caiomansho.domain

import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(username: String, password: String): Flow<Boolean>
}