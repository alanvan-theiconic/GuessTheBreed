package com.alanvan.gues_the_breed.multiple_choice.model

import androidx.compose.runtime.Immutable

@Immutable
data class UiMultipleChoiceQuestion(
    val breedImages: List<String>,
    val options: List<UiOption>,
    val showAnswer: Boolean
) {
    data class UiOption(
        val value: String,
        val isCorrect: Boolean,
        val isSelected: Boolean
    )
}
