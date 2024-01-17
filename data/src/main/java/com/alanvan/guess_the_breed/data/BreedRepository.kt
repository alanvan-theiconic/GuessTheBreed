package com.alanvan.guess_the_breed.data

import com.alanvan.guess_the_breed.data.model.BreedImage
import io.reactivex.rxjava3.core.Single

interface BreedRepository {
    fun getRandomBreedImage(): Single<BreedImage>
    fun getBreedImages(breedName: String?): Single<List<String>>
    fun getAllBreeds(): Single<List<String>>
}
