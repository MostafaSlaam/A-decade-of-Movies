package com.task.a_decade_of_movies.features.home.presentation.view

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.presentation.viewmodel.HomeEvent
import com.task.a_decade_of_movies.features.home.presentation.viewmodel.HomeViewModel
import com.task.a_decade_of_movies.features.main.MainViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    viewModel: HomeViewModel = hiltViewModel(),

    ) {

    LaunchedEffect(key1 = Unit, block = {
        viewModel.onEvent(HomeEvent.GetDataFromFile)
    })


    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                modifier = Modifier.weight(1f),
            ) {

                when (viewModel.homeState.value) {
                    is DataState.Failure -> {
//                    item {
//                        EmptyData(
//                            modifier = Modifier.fillParentMaxSize(),
//                            painter = painterResource(id = R.drawable.ic_no_internet),
//                            text = stringResource(id = R.string.strEmptyData)
//                        )
//                    }
                    }

                    is DataState.Loading -> {
                        item {
                            Column {
                                Box(modifier = Modifier.fillParentMaxSize()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center),
                                        color = MaterialTheme.colors.primary
                                    )
                                }
                            }
                        }
                    }

                    is DataState.Success -> {
                        items((viewModel.homeState.value as DataState.Success<List<MovieModel>>).data.size) {
                            Text(text = it.toString())
                        }

                    }


                    else -> {}

                }

            }

        }
    }
}





