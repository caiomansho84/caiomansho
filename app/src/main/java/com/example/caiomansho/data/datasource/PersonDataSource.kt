package com.example.caiomansho.data.datasource

import android.content.Context
import com.example.caiomansho.R
import com.example.caiomansho.data.Person
import com.example.caiomansho.data.repository.PersonRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context
): PersonRepository {

    private val persons: List<Person> by lazy { parsePersonsList() }

    override suspend fun getPersons(): List<Person> {
        delay(1500)
        return persons
    }

    override suspend fun getPersonById(id: String): Person {
        return persons.first { it.id == id }
    }

    override suspend fun searchPersons(query: String): List<Person> {
        return persons.filter { it.name.contains(query) }
    }

    private fun parsePersonsList(): List<Person> {
        val inputStream = context.resources.openRawResource(R.raw.person)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Json.decodeFromString(
            deserializer = ListSerializer(Person.serializer()),
            string = jsonString
        ).map {
            Person(
                id = it.id,
                name = it.name
            )
        }
    }

}