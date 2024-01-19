package com.alanvan.guess_the_breed.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class BreedRepositoryImpl(
    private val service: BreedService,
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : BreedRepository {

    companion object {
        const val BREED_NAME_MAX_SIZE = 2
    }

    private val allBreedsSubject: BehaviorSubject<List<String>> = BehaviorSubject.create()

    private var loadAllBreedsDisposable: Disposable? = null

    override fun getBreedImages(breedName: String?): Single<List<String>> {
        return breedName?.let {
            val breedInfo = breedName.split("-")
            when (breedInfo.size) {
                1 -> {
                    service.getImagesForBreed(breedInfo.first())
                }

                BREED_NAME_MAX_SIZE -> {
                    service.getImagesForBreed(breedInfo.first(), breedInfo[1])
                }

                else -> {
                    Single.error(Exception("Breed name is invalid"))
                }
            }.map { it.imageUrls }.subscribeOn(ioScheduler)
        } ?: Single.error(Exception("Breed name cannot be null"))
    }

    override fun getAllBreeds(): Observable<List<String>> {
        return allBreedsSubject.hide()
    }

    override fun loadAllBreeds() {
        loadAllBreedsDisposable?.dispose()
        loadAllBreedsDisposable = service.getAllBreeds().map { response ->
            response.message.entries.fold(mutableListOf<String>()) { acc, entry ->
                if (entry.value.isNotEmpty()) {
                    entry.value.forEach {
                        acc.add("${entry.key}-${it}")
                    }
                } else {
                    acc.add(entry.key)
                }
                acc
            }.toList()
        }.subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe({
                allBreedsSubject.onNext(it)
            }, {
                allBreedsSubject.onNext(emptyList())
            })
    }
}
