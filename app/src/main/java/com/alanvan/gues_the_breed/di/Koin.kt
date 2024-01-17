package com.alanvan.gues_the_breed.di

import com.alanvan.gues_the_breed.MainViewModel
import com.alanvan.guess_the_breed.data.BreedRepository
import com.alanvan.guess_the_breed.data.BreedRepositoryImpl
import com.alanvan.guess_the_breed.data.BreedService
import com.alanvan.guess_the_breed.domain.GetBreedQuestionUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val IO = "io"
const val MAIN_THREAD = "mainThread"
const val BASE_URL = "https://dog.ceo/"

val appModule = module {
    single<Scheduler>(named(MAIN_THREAD)) { AndroidSchedulers.mainThread() }
    single<Scheduler>(named(IO)) { Schedulers.io() }
    single<BreedRepository> { BreedRepositoryImpl(get(), get(named(IO))) }
    viewModel { MainViewModel(GetBreedQuestionUseCase(get()), get(named(MAIN_THREAD))) }
}

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(BreedService::class.java)
    }
}
