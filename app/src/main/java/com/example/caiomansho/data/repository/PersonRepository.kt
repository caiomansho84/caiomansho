package com.example.caiomansho.data.repository

import com.example.caiomansho.data.Person

interface PersonRepository {
    suspend fun getPersons(): List<Person>
    suspend fun searchPersons(query: String): List<Person>
    suspend fun getPersonById(id: String): Person
}