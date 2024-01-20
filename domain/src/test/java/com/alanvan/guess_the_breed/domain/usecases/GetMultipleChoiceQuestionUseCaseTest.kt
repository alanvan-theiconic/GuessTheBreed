package com.alanvan.guess_the_breed.domain.usecases

import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestionInput
import com.alanvan.guess_the_breed.domain.repository.BreedRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetMultipleChoiceQuestionUseCaseTest {
    private lateinit var useCase: GetMultipleChoiceQuestionUseCase
    private val breedRepository = mockk<BreedRepository>()

    @Before
    fun setUp() {
        useCase = GetMultipleChoiceQuestionUseCase(breedRepository)
    }

    @Test
    fun `execute should throw RuntimeException when breed list is empty`() {
        every {
            breedRepository.getAllBreeds()
        } returns Observable.just(emptyList())

        val input = MultipleChoiceQuestionInput(3, 2)
        useCase.execute(input).test().assertError(RuntimeException::class.java)
    }

    @Test
    fun `execute should throw IllegalArgumentException when number of options is larger than breed count`() {
        every {
            breedRepository.getAllBreeds()
        } returns Observable.just(listOf("Breed1", "Breed2"))

        val input = MultipleChoiceQuestionInput(5, 2)
        useCase.execute(input).test().assertError(IllegalArgumentException::class.java)
    }

    @Test
    fun `execute should return successful result`() {
        val breeds = listOf("Breed1", "Breed2", "Breed3")
        val images = listOf("Image1", "Image2")
        val input = MultipleChoiceQuestionInput(numberOfOptions = 3, maxNumberOfImages = 2)

        every { breedRepository.getAllBreeds() } returns Observable.just(breeds)
        every { breedRepository.getBreedImages(any()) } returns Single.just(images)

        val testObserver = useCase.execute(input).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val result = testObserver.values().first()
        with(result) {
            assertEquals(2, this.images.size)
            assertEquals(3, this.options.size)
            assertEquals(1, this.options.filter { it.isCorrect }.size)
        }
    }
}
