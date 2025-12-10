package com.example.caiomansho.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caiomansho.data.Person
import com.example.caiomansho.domain.GetBalanceUseCase
import com.example.caiomansho.domain.GetLoggedUseCase
import com.example.caiomansho.domain.GetPersonsUseCase
import com.example.caiomansho.domain.SearchPersonsUseCase
import com.example.caiomansho.ui.uistate.GenericUiState
import com.example.caiomansho.ui.uistate.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPersonsUseCase: GetPersonsUseCase,
    private val searchPersonsUseCase: SearchPersonsUseCase,
    private val getLoggedUseCase: GetLoggedUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GenericUiState<List<Person>>>(GenericUiState.Loading)
    val uiState: StateFlow<GenericUiState<List<Person>>> = _uiState
    var userUiState by mutableStateOf(UserUiState())
        private set

    var query by mutableStateOf("")

    fun loadUsername() = viewModelScope.launch {
        getLoggedUseCase.invoke()
            .collect { username ->
                userUiState = userUiState.copy(username = username)
            }
    }

    fun loadBalance() = viewModelScope.launch {
        getBalanceUseCase.invoke()
            .collect { balance ->
                userUiState = userUiState.copy(balance = balance)
            }
    }

    fun loadPersons() = viewModelScope.launch {
        getPersonsUseCase.invoke()
            .onStart { _uiState.value = GenericUiState.Loading }
            .collect { persons ->
                _uiState.value = GenericUiState.Success(persons)
            }
    }

    fun searchPersons() = viewModelScope.launch {
        searchPersonsUseCase.invoke(query)
            .collect { books ->
                _uiState.value = GenericUiState.Success(books)
            }
    }

}