package com.alanvan.guess_the_breed.data.model

import com.google.gson.annotations.SerializedName

data class BreedImageResponse(
    @SerializedName("message") val imageUrl: String?
)
