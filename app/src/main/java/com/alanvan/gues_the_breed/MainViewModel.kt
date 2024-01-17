package com.alanvan.gues_the_breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.guess_the_breed.domain.common.SingleUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable

class MainViewModel(
    private val getAllBreedsUseCase: SingleUseCase<Unit, List<String>>,
    private val mainScheduler: Scheduler
) : ViewModel() {
    private val _allBreeds: MutableLiveData<List<String>> = MutableLiveData()
    val allBreeds: LiveData<List<String>> get() = _allBreeds

    private var loadAllBreedsDisposable: Disposable? = null

    fun loadAllBreeds() {
        loadAllBreedsDisposable?.dispose()
        loadAllBreedsDisposable = getAllBreedsUseCase.execute(Unit)
            .observeOn(mainScheduler)
            .subscribe({
                println("dmdmdm --- ${it.size}")
            }, {

            })
    }

    override fun onCleared() {
        super.onCleared()
        loadAllBreedsDisposable?.dispose()
    }
}
