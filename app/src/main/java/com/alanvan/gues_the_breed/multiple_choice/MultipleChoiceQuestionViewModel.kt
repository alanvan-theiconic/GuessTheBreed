package com.alanvan.gues_the_breed.multiple_choice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.gues_the_breed.multiple_choice.model.MultipleChoiceScreenState
import com.alanvan.gues_the_breed.multiple_choice.model.UiMultipleChoiceQuestion
import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestion
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable

class MultipleChoiceQuestionViewModel(
    private val getMultipleChoiceQuestionUseCase: ObservableUseCase<Int, MultipleChoiceQuestion>,
    private val mainScheduler: Scheduler
) : ViewModel() {
    companion object {
        private const val NUMBER_OF_OPTIONS = 3
    }

    private val _multipleChoiceScreenState: MutableLiveData<MultipleChoiceScreenState> =
        MutableLiveData()
    val multipleChoiceScreenState: LiveData<MultipleChoiceScreenState> get() = _multipleChoiceScreenState

    private var getMultipleChoiceQuestionDisposable: Disposable? = null

    init {
        getMultipleChoiceQuestion()
    }

    fun getMultipleChoiceQuestion() {
        _multipleChoiceScreenState.value = MultipleChoiceScreenState.Loading
        getMultipleChoiceQuestionDisposable?.dispose()
        getMultipleChoiceQuestionDisposable = getMultipleChoiceQuestionUseCase
            .execute(NUMBER_OF_OPTIONS)
            .observeOn(mainScheduler)
            .subscribe({ question ->
                _multipleChoiceScreenState.value = MultipleChoiceScreenState.Success(
                    question = UiMultipleChoiceQuestion(
                        breedImages = question.images,
                        options = question.options.map {
                            UiMultipleChoiceQuestion.UiOption(
                                isCorrect = it.isCorrect,
                                value = it.value,
                                isSelected = false
                            )
                        },
                        showAnswer = false
                    )
                )
            }, {
                _multipleChoiceScreenState.value = MultipleChoiceScreenState.Error
            })
    }
}
