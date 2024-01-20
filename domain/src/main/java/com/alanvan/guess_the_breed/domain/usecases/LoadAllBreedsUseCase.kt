package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.domain.common.UseCase
import com.alanvan.guess_the_breed.domain.repository.BreedRepository

class LoadAllBreedsUseCase(
    private val repository: BreedRepository
) : UseCase<Unit, Unit> {
    override fun execute(commandArgument: Unit) {
        repository.loadAllBreeds()
    }
}
