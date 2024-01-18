package com.alanvan.gues_the_breed.multiple_choice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alanvan.gues_the_breed.R
import com.alanvan.gues_the_breed.common.AppSnackBar
import com.alanvan.gues_the_breed.common.BreedImagePager
import com.alanvan.gues_the_breed.common.DefaultButton
import com.alanvan.gues_the_breed.common.GuessTheBreedAppBar
import com.alanvan.gues_the_breed.common.OptionCard
import com.alanvan.gues_the_breed.common.QuestionNavigation
import com.alanvan.gues_the_breed.multiple_choice.model.MultipleChoiceScreenState
import com.alanvan.gues_the_breed.ui.theme.successGreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MultipleChoiceQuestionScreen(
    navController: NavController
) {
    val multipleChoiceQuestionViewModel = koinViewModel<MultipleChoiceQuestionViewModel>()
    val multipleChoiceScreenState by multipleChoiceQuestionViewModel.multipleChoiceScreenState.observeAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(id = R.string.multiple_choice_question_reselected)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
        GuessTheBreedAppBar(
            title = stringResource(id = R.string.multiple_choice_question_title),
            hasNavigationButton = true,
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }, snackbarHost = {
        SnackbarHost(hostState = snackBarHostState) {
            AppSnackBar(
                message = it.visuals.message,
                startIcon = Icons.Filled.Info
            )
        }
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
                        val hapticFeedback = LocalHapticFeedback.current
                        state.question.options.forEach { option ->
                            OptionCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                                    .weight(1f),
                                title = option.displayName,
                                colors = when {
                                    state.question.showAnswer && option.isCorrect -> {
                                        CardDefaults.cardColors(
                                            containerColor = successGreen,
                                            contentColor = Color.White
                                        )
                                    }

                                    state.question.showAnswer && !option.isCorrect && option.isSelected -> {
                                        CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.error,
                                            contentColor = MaterialTheme.colorScheme.onError
                                        )
                                    }

                                    else -> {
                                        CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                            ) {
                                if (!state.question.showAnswer) {
                                    multipleChoiceQuestionViewModel.selectOption(option)
                                    if (option.isCorrect) {
                                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                                    }
                                } else {
                                    coroutineScope.launch {
                                        snackBarHostState.showSnackbar(snackBarMessage)
                                    }
                                }
                            }
                        }
                        QuestionNavigation(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = stringResource(id = R.string.next)
                        ) {
                            multipleChoiceQuestionViewModel.loadQuestion()
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
                        style = MaterialTheme.typography.titleMedium
                    )
                    DefaultButton(title = stringResource(id = R.string.home_error_retry)) {
                        multipleChoiceQuestionViewModel.loadQuestion()
                    }
                }

                else -> {
                    // do nothing
                }
            }
        }
    })
}
