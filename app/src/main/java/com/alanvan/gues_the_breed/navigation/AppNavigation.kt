package com.alanvan.gues_the_breed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alanvan.gues_the_breed.common.slideIn
import com.alanvan.gues_the_breed.common.slideOut
import com.alanvan.gues_the_breed.home.HomeScreen
import com.alanvan.gues_the_breed.question.GuessTheBreedScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HOME) {
        composable(
            route = HOME,
            exitTransition = null
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = GUESS_THE_BREED,
            enterTransition = slideIn(),
            exitTransition = slideOut(),
            popEnterTransition = slideIn(),
            popExitTransition = slideOut()
        ) {
            GuessTheBreedScreen(navController = navController)
        }
    }
}
