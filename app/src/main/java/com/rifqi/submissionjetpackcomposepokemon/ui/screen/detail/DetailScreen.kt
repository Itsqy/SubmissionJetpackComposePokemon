package com.rifqi.submissionjetpackcomposepokemon.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rifqi.submissionjetpackcomposepokemon.ViewModelFactory
import com.rifqi.submissionjetpackcomposepokemon.di.Injection
import com.rifqi.submissionjetpackcomposepokemon.ui.common.UiState
import com.rifqi.submissionjetpackcomposepokemon.ui.theme.SubmissionJetpackComposePokemonTheme

@Composable
fun DetailScreen(
    pokeId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,

    ) {

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->

        when (uiState) {
            is UiState.Error -> {}
            is UiState.Success -> {
                val data = uiState.data
                Log.d("sauhr", "DetailScreen: $pokeId")
                DetailContent(
                    namePokemon = data.namePokemon,
                    imgPokemon = data.imgPokemon,
                    descPokemon = data.descPoekmon,
                    typePokemon = data.typePooke,
                    onBackClick = navigateBack
                )
            }

            UiState.Loading -> { viewModel.getPokeById(pokeId)
                Log.d("sauhr", "DetailScreen: $pokeId")}
        }
    }

}

@Composable
fun DetailContent(
    namePokemon: String,
    imgPokemon: String,
    descPokemon: String,
    typePokemon: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

        Column(

            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
               ,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Box {
                AsyncImage(
                    model = imgPokemon, contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )

            }
            Text(text = namePokemon, fontWeight = FontWeight.Bold)
            Text(text = typePokemon)
            Text(text = descPokemon,modifier=Modifier.padding(16.dp))



        }



    }

}

@Preview
@Composable
fun test() {
    SubmissionJetpackComposePokemonTheme {
        DetailContent(
            "Squirtle",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png",
            "Water",
            "absabsjalbsla",
            onBackClick = { })

    }

}
