package com.rifqi.submissionjetpackcomposepokemon.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rifqi.submissionjetpackcomposepokemon.ViewModelFactory
import com.rifqi.submissionjetpackcomposepokemon.di.Injection
import com.rifqi.submissionjetpackcomposepokemon.model.Pokemon
import com.rifqi.submissionjetpackcomposepokemon.ui.common.UiState
import com.rifqi.submissionjetpackcomposepokemon.ui.components.PokemonItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
    navigateToProfile: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllPokemons()
            }
            is UiState.Success -> {
                HomeContent(
                    pokeList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    navigateToProfile = navigateToProfile
                )
            }
            is UiState.Error -> {}
        }

    }

}

@Composable
fun HomeContent(
    pokeList: List<Pokemon>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    navigateToProfile: () -> Unit
) {

    Scaffold(
        topBar = {
            MyTopBar(navigateToProfile)
        },
        modifier = modifier
    ) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier.padding(it)
        ) {
            items(pokeList) { data ->
                PokemonItem(title = data.namePokemon,
                    photo = data.imgPokemon,
                    type = data.typePooke,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.idPokemon)
                        Log.d("touchAble", "HomeContent:succes = ${data.idPokemon} ")

                    })
            }
        }

    }

}

@Composable
fun MyTopBar(
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = "My Pokemon", modifier.clickable { navigateToProfile })
        },
        actions = {
            IconButton(

                onClick =
                navigateToProfile
            ) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "about_page",

                )
            }

        })
}

