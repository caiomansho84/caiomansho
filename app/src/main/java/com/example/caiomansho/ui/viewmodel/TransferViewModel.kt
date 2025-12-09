package com.example.caiomansho.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caiomansho.data.Person
import com.example.caiomansho.domain.GetBalanceUseCase
import com.example.caiomansho.domain.GetLoggedUseCase
import com.example.caiomansho.domain.GetPersonUseCase
import com.example.caiomansho.domain.GetPersonsUseCase
import com.example.caiomansho.domain.SearchPersonsUseCase
import com.example.caiomansho.ui.uistate.HomeUiState
import com.example.caiomansho.ui.uistate.LoginUiState
import com.example.caiomansho.ui.uistate.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState<Person>>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState<Person>> = _uiState
    var userUiState by mutableStateOf(UserUiState())
        private set


    fun loadBalance() = viewModelScope.launch {
        getBalanceUseCase.invoke()
            .collect { balance ->
                userUiState = userUiState.copy(balance = balance)
            }
    }

    fun loadPersonById(personId: String) = viewModelScope.launch {
        getPersonUseCase.invoke(personId)
            .collect { person ->
                _uiState.value = HomeUiState.Success(person)
            }
    }


}