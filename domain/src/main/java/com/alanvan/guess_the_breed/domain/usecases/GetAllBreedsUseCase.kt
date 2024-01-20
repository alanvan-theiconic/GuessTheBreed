package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import com.alanvan.guess_the_breed.domain.repository.BreedRepository
import io.reactivex.rxjava3.core.Observable

class GetAllBreedsUseCase(
    private val repository: BreedRepository
) : ObservableUseCase<Unit, List<String>> {
    override fun execute(commandArgument: Unit): Observable<List<String>> {
        return repository.getAllBreeds()
    }
}
