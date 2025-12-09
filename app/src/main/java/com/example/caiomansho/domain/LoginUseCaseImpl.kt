package com.example.caiomansho.domain

import com.example.caiomansho.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCaseImpl(
    private val loginRepository: UserRepository
): LoginUseCase {

    override operator fun invoke(username: String, password: String): Flow<Boolean> = flow {
        emit(loginRepository.login(username, password))
    }

}