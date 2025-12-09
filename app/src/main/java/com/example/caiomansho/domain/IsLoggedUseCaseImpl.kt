package com.example.caiomansho.domain

import com.example.caiomansho.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IsLoggedUseCaseImpl(
    private val loginRepository: LoginRepository
): IsLoggedUseCase {

    override fun invoke(): Flow<Boolean>  = flow {
        emit(loginRepository.isLogged())
    }

}