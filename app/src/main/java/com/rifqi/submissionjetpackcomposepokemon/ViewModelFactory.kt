package com.rifqi.submissionjetpackcomposepokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifqi.submissionjetpackcomposepokemon.data.PokeRepository
import com.rifqi.submissionjetpackcomposepokemon.ui.screen.detail.DetailViewModel
import com.rifqi.submissionjetpackcomposepokemon.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: PokeRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}