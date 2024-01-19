package com.alanvan.gues_the_breed.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.gues_the_breed.home.model.HomeScreenState
import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import com.alanvan.guess_the_breed.domain.common.UseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeViewModel(
    getAllBreedsUseCase: ObservableUseCase<Unit, List<String>>,
    private val loadAllBreedsUseCase: UseCase<Unit, Unit>,
    mainScheduler: Scheduler
) : ViewModel() {
    private val _homeScreenState: MutableLiveData<HomeScreenState> = MutableLiveData()
    val homeScreenState: LiveData<HomeScreenState> get() = _homeScreenState

    @VisibleForTesting
    val disposables = CompositeDisposable()

    init {
        loadAllBreeds()
        disposables.add(
            getAllBreedsUseCase.execute(Unit)
                .observeOn(mainScheduler)
                .subscribe({ breedList ->
                    if (breedList.isNotEmpty()) {
                        _homeScreenState.value = HomeScreenState.Success
                    } else {
                        _homeScreenState.value = HomeScreenState.Error
                    }
                }, {
                    _homeScreenState.value = HomeScreenState.Error
                })
        )
    }

    fun loadAllBreeds() {
        _homeScreenState.value = HomeScreenState.Loading
        loadAllBreedsUseCase.execute(Unit)
    }
}
