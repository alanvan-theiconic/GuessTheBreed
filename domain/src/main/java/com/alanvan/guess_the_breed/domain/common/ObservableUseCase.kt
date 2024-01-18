package com.alanvan.guess_the_breed.domain.common

import io.reactivex.rxjava3.core.Observable

interface ObservableUseCase<T, R : Any> : UseCase<T, Observable<R>>
