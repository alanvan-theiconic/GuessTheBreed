package com.alanvan.guess_the_breed.domain.model

data class MultipleChoiceQuestion(
    val images: List<String>,
    val options: List<Option>
) {
    data class Option(
        val value: String,
        val isCorrect: Boolean
    )
}
