package com.example.caiomansho.domain

import com.example.caiomansho.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IsLoggedUseCaseImpl(
    private val loginRepository: UserRepository
): IsLoggedUseCase {

    override fun invoke(): Flow<Boolean>  = flow {
        emit(loginRepository.isLogged())
    }

}