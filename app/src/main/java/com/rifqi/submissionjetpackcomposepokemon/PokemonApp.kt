package com.rifqi.submissionjetpackcomposepokemon

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rifqi.submissionjetpackcomposepokemon.ui.navigations.Screen
import com.rifqi.submissionjetpackcomposepokemon.ui.screen.about.AboutScreen
import com.rifqi.submissionjetpackcomposepokemon.ui.screen.detail.DetailScreen
import com.rifqi.submissionjetpackcomposepokemon.ui.screen.home.HomeScreen


@Composable
fun PokemonApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(modifier = modifier) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { pokeId ->
                        navController.navigate(Screen.DetailPokemon.createRoute(pokeId))
                    },
                    navigateToProfile = {
                        navController.navigate(Screen.Profile.route){
                            popUpTo(navController.graph.findStartDestination().id)
                        }
                    },
                )
            }

            composable(route = Screen.Profile.route) {
                AboutScreen(
                )
            }

            composable(
                route = Screen.DetailPokemon.route,
                arguments = listOf(navArgument("pokeId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("pokeId") ?: -1L
                DetailScreen(
                    pokeId = id,
                    navigateBack = {
                        navController.navigateUp()
                    })


            }
        }


    }

}