package com.alanvan.gues_the_breed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import com.alanvan.gues_the_breed.navigation.AppNavigation
import com.alanvan.gues_the_breed.ui.theme.GuessTheBreedTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            GuessTheBreedTheme {
                AppNavigation()
            }
        }
    }
}
