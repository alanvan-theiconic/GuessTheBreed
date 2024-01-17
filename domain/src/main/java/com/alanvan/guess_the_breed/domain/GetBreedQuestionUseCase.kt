package com.alanvan.guess_the_breed.domain

import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.domain.model.BreedQuestion
import io.reactivex.Single

class GetBreedQuestionUseCase(
    private val breedRepository: BreedRepository
) : SingleUseCase<Unit, BreedQuestion> {
    override suspend fun execute(commandArgument: Unit): Single<BreedQuestion> {
        return breedRepository.getRandomBreedImage().flatMap { breedImage ->
            breedRepository.getBreedImages(breedImage.breedName).map { images ->
                BreedQuestion(
                    breedAnswer = breedImage.breedName.orEmpty(),
                    images = images
                )
            }
        }
    }
}
