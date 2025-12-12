package com.example.caiomansho.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caiomansho.R
import com.example.caiomansho.domain.IsLoggedUseCase
import com.example.caiomansho.domain.LoginUseCase
import com.example.caiomansho.ui.uistate.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val isLoggedUseCase: IsLoggedUseCase,
    @ApplicationContext private val context: Context
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
                        errorMsg = context.getString(R.string.login_failed),
                        email = "",
                        password = ""
                    )
                }

            }

    }

    fun isLogged() = viewModelScope.launch {
        isLoggedUseCase.invoke()
            .collect { isLogged ->
                uiState = uiState.copy(isLogged = isLogged)
            }
    }

}
