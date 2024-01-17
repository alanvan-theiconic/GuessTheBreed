package com.alanvan.gues_the_breed.free_response.model

import androidx.compose.runtime.Immutable

@Immutable
data class UiFreeResponseQuestion(
    val breedAnswer: String,
    val breedImages: List<String>
)
