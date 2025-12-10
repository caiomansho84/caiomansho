package com.example.caiomansho.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.caiomansho.ui.uistate.GenericUiState
import com.example.caiomansho.ui.viewmodel.TransferViewModel
import com.example.caiomansho.util.CurrencyMaskTransformation
import java.text.NumberFormat
import java.util.Locale


@Composable
fun TransferScreen(
    personId: String,
    navController: NavController,
    transferViewModel: TransferViewModel = hiltViewModel(),
) {
    val uiState by transferViewModel.uiState.collectAsState()
    val transferUiState by transferViewModel.transferUiState.collectAsState()

    LaunchedEffect(personId) {
        transferViewModel.loadPersonById(personId)
    }

    if(transferUiState is GenericUiState.Success) {
        LaunchedEffect(Unit) {
            navController.navigateUp()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when(uiState) {
            is GenericUiState.Loading -> LoadingScreen()
            is GenericUiState.Success ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(text = "Transferir para ${(uiState as GenericUiState.Success).data.name}")
                    CurrencyTextField(
                        value = transferViewModel.value,
                        onValueChange = { transferViewModel.value = it }
                    )
                    Button(
                        onClick = {
                            transferViewModel.transfer()
                                  },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = transferUiState != GenericUiState.Loading
                    ) {
                        if (transferUiState is GenericUiState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(22.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Transferir")
                        }
                    }
                    if (transferUiState is GenericUiState.Error) {
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = (transferUiState as GenericUiState.Error).message,
                            color = Color.Red
                        )
                    }
                }
            is GenericUiState.Error -> {}
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
