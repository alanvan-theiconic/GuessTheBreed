package com.alanvan.guess_the_breed.domain.model

data class MultipleChoiceQuestionInput(
    val numberOfOptions: Int,
    val maxNumberOfImages: Int
)
