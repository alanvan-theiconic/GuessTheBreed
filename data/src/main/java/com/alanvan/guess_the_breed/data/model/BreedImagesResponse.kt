package com.alanvan.guess_the_breed.data.model

import com.google.gson.annotations.SerializedName

data class BreedImagesResponse(
    @SerializedName("message") val imageUrls: List<String>
)
