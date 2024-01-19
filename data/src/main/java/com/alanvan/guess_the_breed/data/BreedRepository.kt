package com.alanvan.guess_the_breed.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface BreedRepository {
    fun getBreedImages(breedName: String?): Single<List<String>>
    fun loadAllBreeds()
    fun getAllBreeds(): Observable<List<String>>
}
