package com.example.caiomansho.domain

import com.example.caiomansho.data.Person
import com.example.caiomansho.data.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchPersonsUseCaseImpl(
    private val personRepository: PersonRepository
): SearchPersonsUseCase {

    override operator fun invoke(name: String): Flow<List<Person>> = flow {
        emit(personRepository.searchPersons(name))
    }.flowOn(Dispatchers.IO)

}