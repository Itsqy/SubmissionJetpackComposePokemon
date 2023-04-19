package com.rifqi.submissionjetpackcomposepokemon.di

import com.rifqi.submissionjetpackcomposepokemon.data.PokeRepository


object Injection {
    fun provideRepository(): PokeRepository {
        return PokeRepository().getInstance()
    }
}