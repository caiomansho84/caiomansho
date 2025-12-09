package com.example.caiomansho.domain

import com.example.caiomansho.data.Person
import com.example.caiomansho.data.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPersonUseCaseImpl(
    private val personRepository: PersonRepository
): GetPersonUseCase {

    override operator fun invoke(personId: String): Flow<Person> = flow {
        emit(personRepository.getPersonById(personId))

    }
}