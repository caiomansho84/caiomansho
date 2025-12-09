package com.example.caiomansho.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caiomansho.domain.LoginUseCase
import com.example.caiomansho.ui.uistate.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(value: String) {
        uiState = uiState.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        uiState = uiState.copy(password = value)
    }

    fun login() = viewModelScope.launch {

        loginUseCase.invoke(username = uiState.email, password = uiState.password)
            .onStart { uiState = uiState.copy(isLoading = true, errorMsg = null) }
            .collect { success ->
                uiState = if (success) {
                    uiState.copy(isLoading = false, isLogged = true)
                } else {
                    uiState.copy(
                        isLoading = false,
                        errorMsg = "Credenciais inválidas",
                        email = "",
                        password = ""
                    )
                }

            }

    }

}
