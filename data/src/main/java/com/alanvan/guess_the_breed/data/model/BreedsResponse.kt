package com.alanvan.guess_the_breed.data.model

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("message") val message: Map<String, List<String>>
)
