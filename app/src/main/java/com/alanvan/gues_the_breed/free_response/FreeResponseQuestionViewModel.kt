package com.alanvan.gues_the_breed.free_response

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.gues_the_breed.free_response.model.UiFreeResponseQuestion
import com.alanvan.guess_the_breed.domain.common.SingleUseCase
import com.alanvan.guess_the_breed.domain.model.FreeResponseQuestion
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable

class FreeResponseQuestionViewModel(
    private val getFreeResponseQuestionUseCase: SingleUseCase<Unit, FreeResponseQuestion>,
    private val mainScheduler: Scheduler
) : ViewModel() {
    private val _freeResponseQuestion: MutableLiveData<UiFreeResponseQuestion> = MutableLiveData()
    val freeResponseQuestion: LiveData<UiFreeResponseQuestion> get() = _freeResponseQuestion

    private var freeResponseQuestionDisposable: Disposable? = null

    fun loadFreeResponseQuestion() {
        freeResponseQuestionDisposable?.dispose()
        freeResponseQuestionDisposable = getFreeResponseQuestionUseCase.execute(Unit)
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
