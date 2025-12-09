package com.example.caiomansho.domain

import com.example.caiomansho.data.Person
import com.example.caiomansho.data.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPersonsUseCaseImpl(
    private val personRepository: PersonRepository
): GetPersonsUseCase {

    override operator fun invoke(): Flow<List<Person>> = flow {
        emit(personRepository.getPersons())

    }
}