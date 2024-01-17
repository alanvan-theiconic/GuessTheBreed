package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.domain.common.SingleUseCase
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestion
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestionInput
import io.reactivex.rxjava3.core.Single

class GetMultipleChoiceQuestionUseCase(
    private val breedRepository: BreedRepository
) : SingleUseCase<MultipleChoiceQuestionInput, MultipleChoiceQuestion> {
    override fun execute(input: MultipleChoiceQuestionInput): Single<MultipleChoiceQuestion> {
        if (input.numberOfOptions > input.allBreeds.size) {
            throw IllegalArgumentException("Number of options must be smaller than breed count")
        }
        val breedsInQuestion = input.allBreeds.shuffled().take(input.numberOfOptions)
        val correctBreedOption = breedsInQuestion.random()
        return breedRepository.getBreedImages(correctBreedOption).map { images ->
            MultipleChoiceQuestion(
                images = images,
                options = breedsInQuestion.map {
                    MultipleChoiceQuestion.Option(
                        value = it,
                        isCorrect = it == correctBreedOption
                    )
                }
            )
        }
    }
}
