package com.alanvan.gues_the_breed.question

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alanvan.gues_the_breed.question.model.MultipleChoiceScreenState
import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import com.alanvan.guess_the_breed.domain.common.UseCase
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestion
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestionInput
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GuessTheBreedViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: GuessTheBreedViewModel
    private lateinit var getMultipleChoiceQuestionUseCase: ObservableUseCase<MultipleChoiceQuestionInput, MultipleChoiceQuestion>
    private lateinit var getDisplayNameFromBreedNameUseCase: UseCase<String, String>
    private val mainScheduler = Schedulers.trampoline()

    @Before
    fun setup() {
        getMultipleChoiceQuestionUseCase = mockk {
            every { execute(any()) } returns Observable.just(
                MultipleChoiceQuestion(
                    images = listOf("https://www.google.com"),
                    options = listOf(
                        MultipleChoiceQuestion.Option(
                            "jack-russell",
                            isCorrect = true
                        )
                    )
                )
            )
        }
        getDisplayNameFromBreedNameUseCase = mockk {
            every { execute(any()) } returns "Jack Russell"
        }
    }

    @Test
    fun `init - getMultipleChoiceQuestionUseCase is executed successfully`() {
        viewModel = GuessTheBreedViewModel(
            getMultipleChoiceQuestionUseCase,
            getDisplayNameFromBreedNameUseCase,
            mainScheduler
        )
        verify(exactly = 1) {
            getMultipleChoiceQuestionUseCase.execute(any())
            getDisplayNameFromBreedNameUseCase.execute(any())
        }
        assertTrue(viewModel.screenState.value is MultipleChoiceScreenState.Success)
    }

    @Test
    fun `init - getMultipleChoiceQuestionUseCase fails`() {
        every {
            getMultipleChoiceQuestionUseCase.execute(any())
        } returns Observable.error(Exception("Uh oh!"))
        viewModel = GuessTheBreedViewModel(
            getMultipleChoiceQuestionUseCase,
            getDisplayNameFromBreedNameUseCase,
            mainScheduler
        )
        verify(exactly = 1) {
            getMultipleChoiceQuestionUseCase.execute(any())
        }
        verify(exactly = 0) {
            getDisplayNameFromBreedNameUseCase.execute(any())
        }
        assertTrue(viewModel.screenState.value is MultipleChoiceScreenState.Error)
    }

    @Test
    fun `selectOption correctly set screenState`() {
        viewModel = GuessTheBreedViewModel(
            getMultipleChoiceQuestionUseCase,
            getDisplayNameFromBreedNameUseCase,
            mainScheduler
        )
        val option =
            (viewModel.screenState.value as MultipleChoiceScreenState.Success).question.options.first()
        assertFalse(option.isSelected)
        viewModel.selectOption(option)
        val updatedOption =
            (viewModel.screenState.value as MultipleChoiceScreenState.Success).question.options.first()
        assertTrue(updatedOption.isSelected)
    }
}
