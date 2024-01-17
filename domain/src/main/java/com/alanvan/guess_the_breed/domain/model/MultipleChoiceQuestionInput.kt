package com.alanvan.guess_the_breed.domain.model

data class MultipleChoiceQuestionInput(
    val allBreeds: List<String>,
    val numberOfOptions: Int = 3
)
