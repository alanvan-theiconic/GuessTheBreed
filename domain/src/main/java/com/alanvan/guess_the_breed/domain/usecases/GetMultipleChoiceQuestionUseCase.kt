package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import com.alanvan.guess_the_breed.domain.common.SingleUseCase
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestion
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class GetMultipleChoiceQuestionUseCase(
    private val breedRepository: BreedRepository
) : ObservableUseCase<Int, MultipleChoiceQuestion> {
    override fun execute(numberOfOptions: Int): Observable<MultipleChoiceQuestion> {
        return breedRepository.getAllBreeds().take(1).flatMapSingle { allBreeds ->
            if (allBreeds.isEmpty()) {
                throw RuntimeException("Number of breeds is empty!")
            }
            if (numberOfOptions > allBreeds.size) {
                throw IllegalArgumentException("Number of options must be smaller than breed count")
            }
            val breedsInQuestion = allBreeds.shuffled().take(numberOfOptions)
            val correctBreedOption = breedsInQuestion.random()
            breedRepository.getBreedImages(correctBreedOption).map { images ->
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
}
