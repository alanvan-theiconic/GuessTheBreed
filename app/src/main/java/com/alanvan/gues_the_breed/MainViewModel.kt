package com.alanvan.gues_the_breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.gues_the_breed.model.UiBreedQuestion
import com.alanvan.guess_the_breed.domain.common.SingleUseCase
import com.alanvan.guess_the_breed.domain.model.BreedQuestion
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable

class MainViewModel(
    private val getBreedQuestionUseCase: SingleUseCase<Unit, BreedQuestion>,
    private val mainScheduler: Scheduler
) : ViewModel() {

    private val _breedQuestion: MutableLiveData<UiBreedQuestion> = MutableLiveData()
    val breedQuestion: LiveData<UiBreedQuestion> get() = _breedQuestion

    private var breedQuestionDisposable: Disposable? = null

    fun loadBreedQuestion() {
        breedQuestionDisposable?.dispose()
        breedQuestionDisposable = getBreedQuestionUseCase.execute(Unit)
            .observeOn(mainScheduler)
            .subscribe({
                       println("dmdmdmd ---- ${it.breedAnswer}")
            }, {

            })
    }

    override fun onCleared() {
        super.onCleared()
    }
}
