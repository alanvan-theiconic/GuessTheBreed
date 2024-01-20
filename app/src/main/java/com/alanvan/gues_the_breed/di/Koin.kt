package com.alanvan.gues_the_breed.di

import com.alanvan.gues_the_breed.home.HomeViewModel
import com.alanvan.gues_the_breed.question.GuessTheBreedViewModel
import com.alanvan.gues_the_breed.usecases.GetDisplayNameFromBreedNameUseCase
import com.alanvan.guess_the_breed.data.BreedRepositoryImpl
import com.alanvan.guess_the_breed.data.BreedService
import com.alanvan.guess_the_breed.domain.repository.BreedRepository
import com.alanvan.guess_the_breed.domain.usecases.GetAllBreedsUseCase
import com.alanvan.guess_the_breed.domain.usecases.GetMultipleChoiceQuestionUseCase
import com.alanvan.guess_the_breed.domain.usecases.LoadAllBreedsUseCase
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
const val BASE_URL = "https://dog.ceo/api/"

val appModule = module {
    single<Scheduler>(named(MAIN_THREAD)) { AndroidSchedulers.mainThread() }
    single<Scheduler>(named(IO)) { Schedulers.io() }
    single<BreedRepository> { BreedRepositoryImpl(get(), get(named(IO)), get(named(MAIN_THREAD))) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            GetAllBreedsUseCase(get()),
            LoadAllBreedsUseCase(get()),
            get(named(MAIN_THREAD))
        )
    }
    viewModel {
        GuessTheBreedViewModel(
            GetMultipleChoiceQuestionUseCase(get()),
            GetDisplayNameFromBreedNameUseCase(),
            get(named(MAIN_THREAD))
        )
    }
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
