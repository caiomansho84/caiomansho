package com.example.caiomansho.data

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: String,
    val name: String
) {
}