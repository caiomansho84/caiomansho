package com.example.caiomansho.domain

import com.example.caiomansho.data.Person
import kotlinx.coroutines.flow.Flow

interface GetPersonUseCase {
    operator fun invoke(personId: String): Flow<Person>
}