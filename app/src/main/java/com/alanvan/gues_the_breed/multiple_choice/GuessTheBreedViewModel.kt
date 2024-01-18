package com.alanvan.gues_the_breed.multiple_choice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.gues_the_breed.multiple_choice.model.MultipleChoiceScreenState
import com.alanvan.gues_the_breed.multiple_choice.model.UiMultipleChoiceQuestion
import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import com.alanvan.guess_the_breed.domain.common.UseCase
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestion
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestionInput
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable

class GuessTheBreedViewModel(
    private val getMultipleChoiceQuestionUseCase: ObservableUseCase<MultipleChoiceQuestionInput, MultipleChoiceQuestion>,
    private val getDisplayNameFromBreedNameUseCase: UseCase<String, String>,
    private val mainScheduler: Scheduler
) : ViewModel() {
    companion object {
        private const val NUMBER_OF_OPTIONS = 3
        private const val MAX_NUMBER_OF_IMAGES = 5
    }

    private val _screenState: MutableLiveData<MultipleChoiceScreenState> =
        MutableLiveData()
    val screenState: LiveData<MultipleChoiceScreenState> get() = _screenState

    private var getMultipleChoiceQuestionDisposable: Disposable? = null

    init {
        loadQuestion()
    }

    fun loadQuestion() {
        _screenState.value = MultipleChoiceScreenState.Loading
        getMultipleChoiceQuestionDisposable?.dispose()
        getMultipleChoiceQuestionDisposable = getMultipleChoiceQuestionUseCase
            .execute(MultipleChoiceQuestionInput(NUMBER_OF_OPTIONS, MAX_NUMBER_OF_IMAGES))
            .observeOn(mainScheduler)
            .subscribe({ question ->
                _screenState.value = MultipleChoiceScreenState.Success(
                    question = UiMultipleChoiceQuestion(
                        breedImages = question.images,
                        options = question.options.map {
                            UiMultipleChoiceQuestion.UiOption(
                                isCorrect = it.isCorrect,
                                value = it.value,
                                displayName = getDisplayNameFromBreedNameUseCase.execute(it.value),
                                isSelected = false
                            )
                        },
                        showAnswer = false
                    )
                )
            }, {
                _screenState.value = MultipleChoiceScreenState.Error
            })
    }

    fun selectOption(selectedOption: UiMultipleChoiceQuestion.UiOption) {
        (_screenState.value as? MultipleChoiceScreenState.Success)?.let {
            _screenState.value = MultipleChoiceScreenState.Success(
                question = it.question.copy(
                    options = it.question.options.map { option ->
                        if (selectedOption == option) {
                            selectedOption.copy(isSelected = true)
                        } else {
                            option
                        }
                    },
                    showAnswer = true
                )
            )
        }
    }
}
