package com.example.caiomansho.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caiomansho.data.Person
import com.example.caiomansho.domain.GetPersonsUseCase
import com.example.caiomansho.domain.SearchPersonsUseCase
import com.example.caiomansho.ui.uistate.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPersonsUseCase: GetPersonsUseCase,
    private val searchPersonsUseCase: SearchPersonsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState<List<Person>>>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState<List<Person>>> = _uiState

    var query by mutableStateOf("")

    fun loadUsername() = viewModelScope.launch {


    }

    fun loadPersons() = viewModelScope.launch {
        getPersonsUseCase.invoke()
            .onStart { _uiState.value = HomeUiState.Loading }
            .collect { persons ->
                _uiState.value = HomeUiState.Success(persons)
            }
    }

    fun searchPersons() = viewModelScope.launch {
        searchPersonsUseCase.invoke(query)
            .collect { books ->
                _uiState.value = HomeUiState.Success(books)
            }

    }
}