package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.domain.common.SingleUseCase
import com.alanvan.guess_the_breed.domain.model.FreeResponseQuestion
import io.reactivex.rxjava3.core.Single

class GetFreeResponseQuestionUseCase(
    private val breedRepository: BreedRepository
) : SingleUseCase<Unit, FreeResponseQuestion> {
    override fun execute(commandArgument: Unit): Single<FreeResponseQuestion> {
        return breedRepository.getRandomBreedImage().flatMap { breedImage ->
            breedRepository.getBreedImages(breedImage.breedName).map { images ->
                FreeResponseQuestion(
                    breedAnswer = breedImage.breedName.orEmpty(),
                    images = images
                )
            }
        }
    }
}
