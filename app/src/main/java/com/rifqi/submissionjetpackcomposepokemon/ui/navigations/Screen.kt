package com.rifqi.submissionjetpackcomposepokemon.ui.navigations

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailPokemon : Screen("home/{pokeId}") {
        fun createRoute(pokeId: Long) = "home/$pokeId"
    }
}