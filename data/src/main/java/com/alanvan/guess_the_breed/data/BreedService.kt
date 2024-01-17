package com.alanvan.guess_the_breed.data

import com.alanvan.guess_the_breed.data.model.BreedImageResponse
import com.alanvan.guess_the_breed.data.model.BreedImagesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedService {
    @GET("api/breed/{name}/images")
    fun getImagesForBreed(@Path("name") breedName: String): Single<BreedImagesResponse>

    @GET("api/breeds/image/random")
    fun getRandomBreedImage(): Single<BreedImageResponse>
}
