package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.domain.common.UseCase

class LoadAllBreedsUseCase(
    private val repository: BreedRepository
): UseCase<Unit, Unit> {
    override fun execute(commandArgument: Unit) {
        repository.loadAllBreeds()
    }
}
