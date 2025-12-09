package com.example.caiomansho.domain

import com.example.caiomansho.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLoggedUseCaseImpl(
    private val userRepository: UserRepository
): GetLoggedUseCase {

    override fun invoke(): Flow<String>  = flow {
        emit(userRepository.getUsername())
    }

}