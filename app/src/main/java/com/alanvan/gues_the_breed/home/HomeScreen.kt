package com.alanvan.gues_the_breed.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alanvan.gues_the_breed.R
import com.alanvan.gues_the_breed.common.DefaultButton
import com.alanvan.gues_the_breed.common.DogAnimation
import com.alanvan.gues_the_breed.common.GuessTheBreedAppBar
import com.alanvan.gues_the_breed.common.OptionCard
import com.alanvan.gues_the_breed.home.model.HomeScreenState
import com.alanvan.gues_the_breed.navigation.GUESS_THE_BREED
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel = koinViewModel<HomeViewModel>()
    val homeScreenState by homeViewModel.homeScreenState.observeAsState()

    Scaffold(topBar = {
        GuessTheBreedAppBar(
            title = stringResource(id = R.string.home_title),
            hasNavigationButton = false
        )
    }, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (homeScreenState) {
                is HomeScreenState.Success -> {
                    DogAnimation(modifier = Modifier
                        .height(500.dp)
                        .padding(top = 24.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    OptionCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        stringResource(id = R.string.multiple_choice_question)
                    ) {
                        navController.navigate(GUESS_THE_BREED)
                    }
                }

                is HomeScreenState.Loading -> {
                    CircularProgressIndicator()
                }

                is HomeScreenState.Error -> {
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = stringResource(id = R.string.home_error_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                    DefaultButton(title = stringResource(id = R.string.home_error_retry)) {
                        homeViewModel.loadAllBreeds()
                    }
                }

                else -> {
                    // do nothing
                }
            }
        }
    })
}
