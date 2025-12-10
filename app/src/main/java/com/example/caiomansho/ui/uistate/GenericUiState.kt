package com.example.caiomansho.ui.uistate

sealed class GenericUiState<out T> {
    object Loading: GenericUiState<Nothing>()
    data class Success<T>(val data: T): GenericUiState<T>()
    data class Error(val message: String): GenericUiState<Nothing>()
}