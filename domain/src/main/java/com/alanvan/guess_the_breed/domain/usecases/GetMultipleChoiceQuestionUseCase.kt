package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import com.alanvan.guess_the_breed.domain.common.takeRandom
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestion
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestionInput
import io.reactivex.rxjava3.core.Observable

class GetMultipleChoiceQuestionUseCase(
    private val breedRepository: BreedRepository
) : ObservableUseCase<MultipleChoiceQuestionInput, MultipleChoiceQuestion> {
    override fun execute(input: MultipleChoiceQuestionInput): Observable<MultipleChoiceQuestion> {
        return breedRepository.getAllBreeds().take(1).flatMapSingle { allBreeds ->
            if (allBreeds.isEmpty()) {
                throw RuntimeException("Number of breeds is empty!")
            }
            if (input.numberOfOptions > allBreeds.size) {
                throw IllegalArgumentException("Number of options must be smaller than breed count")
            }
            val breedsInQuestion = allBreeds.takeRandom(input.numberOfOptions)
            val correctBreedOption = breedsInQuestion.random()
            breedRepository.getBreedImages(correctBreedOption).map { images ->
                MultipleChoiceQuestion(
                    images = images.takeRandom(input.maxNumberOfImages),
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
}
