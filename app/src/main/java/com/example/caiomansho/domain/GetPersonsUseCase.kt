package com.example.caiomansho.domain

import com.example.caiomansho.data.Person
import kotlinx.coroutines.flow.Flow

interface GetPersonsUseCase {
    operator fun invoke(): Flow<List<Person>>
}