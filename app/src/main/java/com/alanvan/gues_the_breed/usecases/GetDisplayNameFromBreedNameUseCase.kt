package com.alanvan.gues_the_breed.usecases

import com.alanvan.guess_the_breed.domain.common.UseCase

class GetDisplayNameFromBreedNameUseCase : UseCase<String, String> {
    override fun execute(breedName: String): String {
        val breedNameParts = breedName.split("-").map { part ->
            part.replaceFirstChar {
                it.uppercase()
            }
        }
        return breedNameParts.reversed().joinToString(" ")
    }
}
