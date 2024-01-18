package com.alanvan.gues_the_breed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alanvan.gues_the_breed.common.slideIn
import com.alanvan.gues_the_breed.common.slideOut
import com.alanvan.gues_the_breed.free_response.FreeResponseQuestionScreen
import com.alanvan.gues_the_breed.home.HomeScreen
import com.alanvan.gues_the_breed.multiple_choice.MultipleChoiceQuestionScreen

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
            route = MULTIPLE_CHOICE,
            enterTransition = slideIn(),
            exitTransition = slideOut(),
            popEnterTransition = slideIn(),
            popExitTransition = slideOut()
        ) {
            MultipleChoiceQuestionScreen(navController = navController)
        }
        composable(
            route = FREE_RESPONSE,
            enterTransition = slideIn(),
            exitTransition = slideOut(),
            popEnterTransition = slideIn(),
            popExitTransition = slideOut()
        ) {
            FreeResponseQuestionScreen(navController = navController)
        }
    }
}
