package com.example.caiomansho.ui.uistate

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val isLogged: Boolean = false
)