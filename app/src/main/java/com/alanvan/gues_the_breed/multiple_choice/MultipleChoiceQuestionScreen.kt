package com.alanvan.gues_the_breed.multiple_choice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
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
import com.alanvan.gues_the_breed.common.BreedImagePager
import com.alanvan.gues_the_breed.common.DefaultButton
import com.alanvan.gues_the_breed.common.GuessTheBreedAppBar
import com.alanvan.gues_the_breed.common.OptionCard
import com.alanvan.gues_the_breed.multiple_choice.model.MultipleChoiceScreenState
import com.alanvan.gues_the_breed.ui.theme.typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun MultipleChoiceQuestionScreen(
    navController: NavController
) {
    val multipleChoiceQuestionViewModel = koinViewModel<MultipleChoiceQuestionViewModel>()
    val multipleChoiceScreenState by multipleChoiceQuestionViewModel.multipleChoiceScreenState.observeAsState()

    Scaffold(topBar = {
        GuessTheBreedAppBar(
            title = stringResource(id = R.string.multiple_choice_question_title),
            hasNavigationButton = true,
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (multipleChoiceScreenState) {
                is MultipleChoiceScreenState.Success -> {
                    (multipleChoiceScreenState as MultipleChoiceScreenState.Success).let { state ->
                        BreedImagePager(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            state.question.breedImages
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        state.question.options.forEach { option ->
                            OptionCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                                title = option.value
                            ) {

                            }
                        }
                    }
                }

                is MultipleChoiceScreenState.Loading -> {
                    CircularProgressIndicator()
                }

                is MultipleChoiceScreenState.Error -> {
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = stringResource(id = R.string.home_error_title),
                        style = typography.titleMedium
                    )
                    DefaultButton(title = stringResource(id = R.string.home_error_retry)) {
                        multipleChoiceQuestionViewModel.getMultipleChoiceQuestion()
                    }
                }

                else -> {
                    // do nothing
                }
            }
        }
    })
}
