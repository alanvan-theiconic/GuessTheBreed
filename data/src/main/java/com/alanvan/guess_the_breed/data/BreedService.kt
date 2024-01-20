package com.alanvan.guess_the_breed.data

import com.alanvan.guess_the_breed.data.model.BreedImagesResponse
import com.alanvan.guess_the_breed.data.model.BreedsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedService {
    @GET("breed/{breedName}/images")
    fun getImagesForBreed(@Path("breedName") breedName: String): Single<BreedImagesResponse>

    @GET("breed/{breedName}/{subBreedName}/images")
    fun getImagesForBreed(
        @Path("breedName") breedName: String,
        @Path("subBreedName") subBreedName: String
    ): Single<BreedImagesResponse>

    @GET("breeds/list/all")
    fun getAllBreeds(): Single<BreedsResponse>
}
