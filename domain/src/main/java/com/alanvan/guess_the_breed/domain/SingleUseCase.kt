package com.alanvan.guess_the_breed.domain

import io.reactivex.Single

interface SingleUseCase<T, R> {
    suspend fun execute(commandArgument: T): Single<R>
}
