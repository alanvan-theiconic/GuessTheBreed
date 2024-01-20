package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.domain.repository.BreedRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import kotlin.test.assertEquals

class GetAllBreedsUseCaseTest {
    @Test
    fun `execute() - returns list of breeds`() {
        val repository: BreedRepository = mockk {
            every { getAllBreeds() } returns Observable.just(listOf("terrier"))
        }
        val useCase = GetAllBreedsUseCase(repository)
        val subscriber = useCase.execute(Unit).test()
        subscriber.assertNoErrors()

        subscriber.assertValueCount(1)
        assertEquals("terrier", subscriber.values().first().first())

        verify(exactly = 1) {
            repository.getAllBreeds()
        }
    }

    @Test
    fun `execute() - throws error`() {
        val error = Exception("Uh oh!")
        val repository: BreedRepository = mockk {
            every { getAllBreeds() } returns Observable.error(error)
        }
        val useCase = GetAllBreedsUseCase(repository)
        val subscriber = useCase.execute(Unit).test()
        subscriber.assertError(error)
    }
}
