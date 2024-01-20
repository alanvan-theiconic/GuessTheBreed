package com.alanvan.guess_the_breed.domain.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface BreedRepository {
    fun getBreedImages(breedName: String?): Single<List<String>>
    fun getAllBreeds(): Observable<List<String>>
    fun loadAllBreeds()
}
