package com.alanvan.gues_the_breed.model

import androidx.compose.runtime.Immutable

@Immutable
data class UiBreedQuestion(
    val breedAnswer: String,
    val breedImages: List<String>
)
