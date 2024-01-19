package com.alanvan.gues_the_breed.usecases

import org.junit.Assert.assertEquals
import org.junit.Test

class GetDisplayNameFromBreedNameUseCaseTest {
    @Test
    fun `execute - breedName has two parts`() {
        val useCase = GetDisplayNameFromBreedNameUseCase()
        val result = useCase.execute("poodle-toy")
        assertEquals("Toy Poodle", result)
    }

    @Test
    fun `execute - breedName has one part`() {
        val useCase = GetDisplayNameFromBreedNameUseCase()
        val result = useCase.execute("komondor")
        assertEquals("Komondor", result)
    }
}
