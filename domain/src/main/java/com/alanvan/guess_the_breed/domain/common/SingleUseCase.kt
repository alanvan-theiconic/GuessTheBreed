package com.alanvan.guess_the_breed.domain.common

import io.reactivex.rxjava3.core.Single

interface SingleUseCase<T, R : Any> : UseCase<T, Single<R>>
