package com.alanvan.gues_the_breed.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alanvan.gues_the_breed.home.HomeViewModel
import com.alanvan.gues_the_breed.home.model.HomeScreenState
import com.alanvan.guess_the_breed.domain.common.ObservableUseCase
import com.alanvan.guess_the_breed.domain.common.UseCase
import com.alanvan.guess_the_breed.domain.usecases.LoadAllBreedsUseCase
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class HomeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var getAllBreedsUseCase: ObservableUseCase<Unit, List<String>>
    private lateinit var loadAllBreedsUseCase: UseCase<Unit, Unit>
    private val mainScheduler = Schedulers.trampoline()

    @Before
    fun setup() {
        getAllBreedsUseCase = mockk {
            every { execute(Unit) } returns Observable.just(listOf("terrier"))
        }
        loadAllBreedsUseCase = mockk {
            every { execute(Unit) } just Runs
        }
        viewModel = HomeViewModel(
            getAllBreedsUseCase,
            loadAllBreedsUseCase,
            mainScheduler
        )
    }

    @Test
    fun `init - loadAllBreeds is called and getAllBreeds successful`() {
        verify(exactly = 1) {
            loadAllBreedsUseCase.execute(Unit)
            getAllBreedsUseCase.execute(Unit)
        }
        assertTrue(viewModel.homeScreenState.value is HomeScreenState.Success)
    }
}
