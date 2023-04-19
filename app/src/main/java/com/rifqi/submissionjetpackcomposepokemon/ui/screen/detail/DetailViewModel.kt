package com.rifqi.submissionjetpackcomposepokemon.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rifqi.submissionjetpackcomposepokemon.data.PokeRepository
import com.rifqi.submissionjetpackcomposepokemon.model.Pokemon
import com.rifqi.submissionjetpackcomposepokemon.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(val pokeRepository: PokeRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Pokemon>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Pokemon>>
        get() = _uiState



    fun getPokeById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(pokeRepository.getPokemonbyId(rewardId))
        }
    }


}