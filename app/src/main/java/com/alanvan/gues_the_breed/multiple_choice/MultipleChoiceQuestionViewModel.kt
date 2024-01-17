package com.alanvan.gues_the_breed.multiple_choice

import androidx.lifecycle.ViewModel
import com.alanvan.guess_the_breed.domain.common.SingleUseCase
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestion
import com.alanvan.guess_the_breed.domain.model.MultipleChoiceQuestionInput
import io.reactivex.rxjava3.core.Scheduler

class MultipleChoiceQuestionViewModel(
    private val getMultipleChoiceQuestionUseCase: SingleUseCase<MultipleChoiceQuestionInput, MultipleChoiceQuestion>,
    private val mainScheduler: Scheduler
) : ViewModel() {

}
