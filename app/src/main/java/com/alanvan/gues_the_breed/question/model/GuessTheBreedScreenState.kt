package com.alanvan.gues_the_breed.question.model

import androidx.compose.runtime.Immutable

@Immutable
sealed class GuessTheBreedScreenState {
    data object Error : GuessTheBreedScreenState()
    data object Loading : GuessTheBreedScreenState()
    data class Success(
        val question: UiMultipleChoiceQuestion
    ) : GuessTheBreedScreenState()
}

@Immutable
data class UiMultipleChoiceQuestion(
    val breedImages: List<String>,
    val options: List<UiOption>,
    val showAnswer: Boolean
) {
    data class UiOption(
        val value: String,
        val displayName: String,
        val isCorrect: Boolean,
        val isSelected: Boolean
    )
}
