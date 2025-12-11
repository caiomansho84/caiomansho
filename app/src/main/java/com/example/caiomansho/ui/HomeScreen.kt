package com.example.caiomansho.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.caiomansho.R
import com.example.caiomansho.data.Person
import com.example.caiomansho.ui.uistate.GenericUiState
import com.example.caiomansho.ui.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by homeViewModel.uiState.collectAsState()
    val userUiState = homeViewModel.userUiState


    LaunchedEffect(Unit) {
        homeViewModel.loadPersons()
        homeViewModel.loadUsername()
        homeViewModel.loadBalance()
    }

    Box(
        modifier = modifier.fillMaxSize(),
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
                    Text(text = String.format(stringResource(R.string.hello), userUiState.username))
                    Text(text = String.format(stringResource(R.string.balance), userUiState.balance))
                    PersonLazyColumn(
                        persons = (uiState as GenericUiState.Success).data,
                        homeViewModel = homeViewModel,
                        navController = navController
                    )
                }
            is GenericUiState.Error -> {}
            else -> {}
        }
    }
}


@Composable
fun PersonLazyColumn(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    persons: List<Person>,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = stringResource(R.string.transfer_for), style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = homeViewModel.query,
            onValueChange = {
                homeViewModel.query = it
                homeViewModel.searchPersons()
            },
            label = { Text(stringResource(R.string.search)) },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
        ) {
            items(
                items = persons
            ) { person ->
                PersonItem(
                    person = person,
                ) {
                    navController.navigate("transfer/${person.id}")
                }
            }
        }
    }
}

@Composable
fun PersonItem(person: Person, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(person.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}