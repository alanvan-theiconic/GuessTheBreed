package com.alanvan.guess_the_breed.data

import com.alanvan.guess_the_breed.data.model.BreedImageResponse
import com.alanvan.guess_the_breed.data.model.BreedImagesResponse
import com.alanvan.guess_the_breed.data.model.BreedsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedService {
    @GET("breed/{name}/images")
    fun getImagesForBreed(@Path("name") breedName: String): Single<BreedImagesResponse>

    @GET("breeds/image/random")
    fun getRandomBreedImage(): Single<BreedImageResponse>

    @GET("breeds/list/all")
    fun getAllBreeds(): Single<BreedsResponse>
}
