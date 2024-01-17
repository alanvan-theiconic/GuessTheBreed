package com.alanvan.guess_the_breed.domain.common

interface UseCase<T, R> {
  fun execute(commandArgument: T): R
}
