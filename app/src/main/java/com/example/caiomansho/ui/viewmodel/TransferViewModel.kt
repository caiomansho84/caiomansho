package com.example.caiomansho.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caiomansho.data.Person
import com.example.caiomansho.domain.GetBalanceUseCase
import com.example.caiomansho.domain.GetPersonUseCase
import com.example.caiomansho.domain.TransferUseCase
import com.example.caiomansho.ui.uistate.GenericUiState
import com.example.caiomansho.util.CurrencyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase,
    private val getBalanceUseCase: GetBalanceUseCase,
    private val transferUseCase: TransferUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GenericUiState<Person>>(GenericUiState.Loading)
    val uiState: StateFlow<GenericUiState<Person>> = _uiState
    private val _transferUiState = MutableStateFlow<GenericUiState<Boolean>?>(
        value = null
    )
    val transferUiState: StateFlow<GenericUiState<Boolean>?> = _transferUiState

    var value by mutableStateOf("R$ 0,00")

    fun transfer() = viewModelScope.launch {
        transferUseCase
            .invoke(CurrencyUtil().currencyTextToFloat(value))
            .onStart { _transferUiState.value = GenericUiState.Loading }
            .catch {
                _transferUiState.value = GenericUiState.Error(it.message ?: "Erro ao transferir")
            }
            .collect { balance ->
                _transferUiState.value = GenericUiState.Success(true)
            }
    }

    fun loadPersonById(personId: String) = viewModelScope.launch {
        getPersonUseCase.invoke(personId)
            .collect { person ->
                _uiState.value = GenericUiState.Success(person)
            }
    }


}