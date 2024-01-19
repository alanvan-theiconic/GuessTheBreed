package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.data.BreedRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class LoadAllBreedsUseCaseTest {

    @Test
    fun execute() {
        val repository: BreedRepository = mockk {
            every { loadAllBreeds() } just Runs
        }
        val useCase = LoadAllBreedsUseCase(repository)
        useCase.execute(Unit)
        verify(exactly = 1) {
            repository.loadAllBreeds()
        }
    }
}
