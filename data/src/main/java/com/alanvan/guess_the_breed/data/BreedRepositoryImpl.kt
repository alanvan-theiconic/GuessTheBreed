package com.alanvan.guess_the_breed.data

import com.alanvan.guess_the_breed.data.model.BreedImage
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class BreedRepositoryImpl(
    private val service: BreedService,
    private val ioScheduler: Scheduler
) : BreedRepository {

    override fun getRandomBreedImage(): Single<BreedImage> {
        return service.getRandomBreedImage().map {
            BreedImage(
                breedName = it.imageUrl?.extractBreedNameFromUrl(),
                imageUrl = it.imageUrl
            )
        }.subscribeOn(ioScheduler)
    }

    override fun getBreedImages(breedName: String?): Single<List<String>> {
        return breedName?.let {
            service.getImagesForBreed(breedName).map {
                it.imageUrls
            }.subscribeOn(ioScheduler)
        } ?: Single.error(Exception("Breed name cannot be empty"))
    }

    override fun getAllBreeds(): Single<List<String>> {
        return service.getAllBreeds().subscribeOn(ioScheduler).map { response ->
            response.message.entries.fold(mutableListOf()) { acc, entry ->
                entry.value.forEach {
                    acc.add("${entry.key}-${it}")
                }
                acc
            }
        }
    }

    private fun String.extractBreedNameFromUrl(): String? {
        val pattern = Regex("/breeds/([^/]+)/")
        val matchResult = pattern.find(this)
        return matchResult?.groups?.get(1)?.value
    }
}
