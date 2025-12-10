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
//    var userUiState by mutableStateOf(UserUiState())
//        private set
    private val _transferUiState = MutableStateFlow<GenericUiState<Boolean>>(GenericUiState.Success(true))
    val transferUiState: StateFlow<GenericUiState<Boolean>> = _transferUiState


    var value by mutableStateOf("R$ 0,00")

//    fun loadBalance() = viewModelScope.launch {
//        getBalanceUseCase.invoke()
//            .collect { balance ->
//                userUiState = userUiState.copy(balance = balance)
//            }
//    }

    fun transfer() = viewModelScope.launch {
        transferUseCase
            .invoke(CurrencyUtil().currencyTextToFloat(value))
            .onStart { _transferUiState.value = GenericUiState.Loading }
            .collect { balance ->
            }
    }

    fun loadPersonById(personId: String) = viewModelScope.launch {
        getPersonUseCase.invoke(personId)
            .collect { person ->
                _uiState.value = GenericUiState.Success(person)
            }
    }


}