package com.alanvan.gues_the_breed.free_response

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun FreeResponseQuestionScreen(
    navController: NavController
) {
    val freeResponseQuestionViewModel = koinViewModel<FreeResponseQuestionViewModel>()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "This is the free response question screen")
    }
}
