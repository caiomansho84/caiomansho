package com.example.caiomansho.domain

import com.example.caiomansho.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCaseImpl(
    private val loginRepository: LoginRepository
): LoginUseCase {

    override operator fun invoke(username: String, password: String): Flow<Boolean> = flow {
        emit(loginRepository.login(username, password))
    }

}