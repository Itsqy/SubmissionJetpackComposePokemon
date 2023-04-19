package com.rifqi.submissionjetpackcomposepokemon.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rifqi.submissionjetpackcomposepokemon.data.PokeRepository
import com.rifqi.submissionjetpackcomposepokemon.model.Pokemon
import com.rifqi.submissionjetpackcomposepokemon.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PokeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Pokemon>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Pokemon>>>
        get() = _uiState

    fun getAllPokemons() {
        viewModelScope.launch {
            repository.getPokemon()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { pokemon ->
                    _uiState.value = UiState.Success(pokemon)
                }

        }
    }
}