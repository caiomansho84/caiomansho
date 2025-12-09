package com.example.caiomansho.ui

import android.R.attr.value
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.caiomansho.ui.uistate.HomeUiState
import com.example.caiomansho.ui.viewmodel.TransferViewModel
import com.example.caiomansho.util.CurrencyMaskTransformation
import java.text.NumberFormat
import java.util.Locale


@Composable
fun TransferScreen(
    personId: String,
    viewModel: TransferViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    var price by remember { mutableStateOf("R$ 0,00") }

    LaunchedEffect(personId) {
        viewModel.loadPersonById(personId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when(uiState) {
            is HomeUiState.Loading -> LoadingScreen()
            is HomeUiState.Success ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(text = "Transferir para ${(uiState as HomeUiState.Success).data.name}")
                    CurrencyTextField(
                        value = price,
                        onValueChange = { price = it }
                    )
                    Button(
                        onClick = {  },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Transferir")
                    }
                }
            is HomeUiState.Error -> {}
            else -> {}
        }
    }

}

@Composable
fun CurrencyTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val locale = Locale("pt", "BR")
    val formatter = remember { NumberFormat.getCurrencyInstance(locale) }

    TextField(
        value = value,
        onValueChange = { newValue ->
            val clean = newValue.replace(Regex("[R$,.\\s]"), "")
            if (clean.isNotEmpty()) {
                try {
                    val parsed = clean.toDouble() / 100
                    onValueChange(formatter.format(parsed))
                } catch (e: Exception) {
                    onValueChange(value)
                }
            } else {
                onValueChange("")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Valor") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CurrencyMaskTransformation()
    )
}
