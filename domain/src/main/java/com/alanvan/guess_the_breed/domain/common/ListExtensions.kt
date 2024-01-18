package com.alanvan.guess_the_breed.domain.common

fun <T> List<T>.takeRandom(count: Int): List<T> {
    return this.shuffled().take(count)
}
