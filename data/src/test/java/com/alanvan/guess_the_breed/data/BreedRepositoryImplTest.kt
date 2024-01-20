package com.alanvan.guess_the_breed.data

import com.alanvan.guess_the_breed.data.model.BreedImagesResponse
import com.alanvan.guess_the_breed.data.model.BreedsResponse
import com.alanvan.guess_the_breed.domain.repository.BreedRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test

class BreedRepositoryImplTest {
    private lateinit var service: BreedService
    private val ioScheduler = Schedulers.trampoline()
    private val mainScheduler = Schedulers.trampoline()
    private lateinit var repository: BreedRepository

    @Test
    fun `getBreedImages() - breedName with one part`() {
        service = mockk {
            every { getImagesForBreed(any()) } returns Single.just(
                BreedImagesResponse(imageUrls = listOf("url"))
            )
        }
        repository = BreedRepositoryImpl(service, ioScheduler, mainScheduler)

        val subscriber = repository.getBreedImages("pug").test()

        subscriber.assertNoErrors()

        assertEquals(1, subscriber.values().first().size)
        verify(exactly = 1) {
            service.getImagesForBreed("pug")
        }
    }

    @Test
    fun `getBreedImages() - breedName with two parts`() {
        service = mockk {
            every { getImagesForBreed(any(), any()) } returns Single.just(
                BreedImagesResponse(imageUrls = listOf("url1", "url2"))
            )
            every { getImagesForBreed(any()) } returns Single.just(
                BreedImagesResponse(imageUrls = listOf("url"))
            )
        }
        repository = BreedRepositoryImpl(service, ioScheduler, mainScheduler)

        val subscriber = repository.getBreedImages("retriever-golden").test()

        subscriber.assertNoErrors()

        assertEquals(2, subscriber.values().first().size)
        verify(exactly = 1) {
            service.getImagesForBreed("retriever", "golden")
        }
    }

    @Test
    fun `getBreedImages() - breed name too long`() {
        service = mockk {
            every { getImagesForBreed(any(), any()) } returns Single.just(
                BreedImagesResponse(imageUrls = listOf("url1", "url2"))
            )
            every { getImagesForBreed(any()) } returns Single.just(
                BreedImagesResponse(imageUrls = listOf("url"))
            )
        }
        repository = BreedRepositoryImpl(service, ioScheduler, mainScheduler)

        val subscriber = repository.getBreedImages("invalid-breed-name").test()

        subscriber.assertError {
            it.message == "Breed name is invalid"
        }

        verify(exactly = 0) {
            service.getImagesForBreed(any())
            service.getImagesForBreed(any(), any())
        }
    }

    @Test
    fun `getBreedImages() - breed name is empty`() {
        service = mockk {
            every { getImagesForBreed(any(), any()) } returns Single.just(
                BreedImagesResponse(imageUrls = listOf("url1", "url2"))
            )
            every { getImagesForBreed(any()) } returns Single.just(
                BreedImagesResponse(imageUrls = listOf("url"))
            )
        }
        repository = BreedRepositoryImpl(service, ioScheduler, mainScheduler)

        val subscriber = repository.getBreedImages(null).test()

        subscriber.assertError {
            it.message == "Breed name cannot be null"
        }

        verify(exactly = 0) {
            service.getImagesForBreed(any())
            service.getImagesForBreed(any(), any())
        }
    }

    @Test
    fun `getBreedImages - service returns error`() {
        service = mockk {
            every { getImagesForBreed(any(), any()) } returns Single.error(Exception("Uh oh!"))
            every { getImagesForBreed(any()) } returns Single.error(Exception("Uh oh!"))
        }
        repository = BreedRepositoryImpl(service, ioScheduler, mainScheduler)

        val subscriber = repository.getBreedImages("pug").test()

        subscriber.assertError {
            it.message == "Uh oh!"
        }
    }

    @Test
    fun `loadAllBreeds - returns expected result`() {
        service = mockk {
            every { getAllBreeds() } returns Single.just(
                BreedsResponse(
                    message = mapOf(
                        "retriever" to listOf("golden"),
                        "pug" to emptyList()
                    )
                )
            )
        }
        repository = BreedRepositoryImpl(service, ioScheduler, mainScheduler)
        repository.loadAllBreeds()

        verify(exactly = 1) {
            service.getAllBreeds()
        }

        val subscriber = repository.getAllBreeds().test()
        assertEquals(1, subscriber.values().size)
        assertEquals("retriever-golden", subscriber.values().first()[0])
        assertEquals("pug", subscriber.values().first()[1])
    }
}
