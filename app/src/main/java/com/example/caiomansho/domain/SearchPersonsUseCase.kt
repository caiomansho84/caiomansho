package com.example.caiomansho.domain

import com.example.caiomansho.data.Person
import kotlinx.coroutines.flow.Flow

interface SearchPersonsUseCase{
    operator fun invoke(name: String): Flow<List<Person>>
}