package com.rifqi.submissionjetpackcomposepokemon.data

import com.rifqi.submissionjetpackcomposepokemon.model.FakePokemon
import com.rifqi.submissionjetpackcomposepokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokeRepository {

    private val pokemon = mutableListOf<Pokemon>()

    init {

        if (pokemon.isEmpty()) {
            FakePokemon.dummyPoke.forEach {
                pokemon.add(it)
            }
        }
    }

    fun getPokemon(): Flow<List<Pokemon>> {
        return flowOf(pokemon)
    }

    fun getPokemonbyId(pokeId: Long): Pokemon {

        return pokemon.first {
            it.idPokemon == pokeId
        }

    }


    @Volatile
    private var instance: PokeRepository? = null

    fun getInstance(): PokeRepository =
        instance ?: synchronized(this) {
            PokeRepository().apply {
                instance = this
            }
        }

}