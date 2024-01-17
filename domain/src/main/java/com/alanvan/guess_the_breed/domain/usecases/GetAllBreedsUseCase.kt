package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.domain.common.SingleUseCase
import io.reactivex.rxjava3.core.Single

class GetAllBreedsUseCase(
    private val repository: BreedRepository
) : SingleUseCase<Unit, List<String>> {
    override fun execute(commandArgument: Unit): Single<List<String>> {
        return repository.getAllBreeds()
    }
}
