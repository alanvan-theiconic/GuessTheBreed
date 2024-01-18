package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import io.reactivex.rxjava3.core.Observable

class GetAllBreedsUseCase(
    private val repository: BreedRepository
) : ObservableUseCase<Unit, List<String>> {
    override fun execute(commandArgument: Unit): Observable<List<String>> {
        return repository.getAllBreeds()
    }
}
