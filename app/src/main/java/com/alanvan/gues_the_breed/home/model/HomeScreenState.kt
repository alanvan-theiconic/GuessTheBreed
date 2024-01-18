package com.alanvan.gues_the_breed.home.model

import androidx.compose.runtime.Immutable

@Immutable
sealed class HomeScreenState {
    data object Error: HomeScreenState()
    data object Success: HomeScreenState()
    data object Loading: HomeScreenState()
}
