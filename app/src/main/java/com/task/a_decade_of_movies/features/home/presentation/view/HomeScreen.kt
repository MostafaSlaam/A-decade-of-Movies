package com.task.a_decade_of_movies.features.home.presentation.view

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.a_decade_of_movies.core.AppPreferences
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.presentation.viewmodel.HomeEvent
import com.task.a_decade_of_movies.features.home.presentation.viewmodel.HomeViewModel
import com.task.a_decade_of_movies.features.main.MainViewModel
import com.task.a_decade_of_movies.route.AppScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    viewModel: HomeViewModel = hiltViewModel(),

    ) {

    LaunchedEffect(key1 = Unit, block = {
        viewModel.onEvent(HomeEvent.GetMovies)
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
            Spacer(modifier = Modifier.size(30.dp))


            TextField(
                value = viewModel.search.value, onValueChange = { new ->
                    viewModel.search.value = new
                    if (viewModel.search.value.length > 3)
                        viewModel.onEvent(HomeEvent.Search)
                }, modifier = Modifier
                    .padding(10.dp),
                placeholder = { Text("Search") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ), shape = RoundedCornerShape(8.dp),
                singleLine = true,
                trailingIcon = {
                    if (viewModel.search.value.isNotEmpty()) {
                        IconButton(onClick = { viewModel.search.value = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.size(20.dp))
            val moviesState = viewModel.homeState.value
            val searchState = viewModel.searchState.value

            LazyColumn(
                modifier = Modifier.weight(1f),
            ) {
                if (viewModel.search.value.isEmpty())
                    when (moviesState) {
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
                            items(moviesState.data.size) {
                                MovieItem(moviesState.data[it]) {
                                    AppPreferences.storeCurrentMovie(moviesState.data[it])
                                    navController.navigate(AppScreen.MovieScreen.route)
                                }
                            }

                        }


                        else -> {}

                    }
                else
                    when (searchState) {
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
                            searchState.data.forEachIndexed { index, movieModel ->
                                if (index==0||(index>0&&movieModel.year!=searchState.data[index-1].year))
                                    item {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth(), textAlign = TextAlign.Center,
                                            text = movieModel.year.toString()
                                        )
                                    }
                                item {
                                    MovieItem(movieModel) {
                                        AppPreferences.storeCurrentMovie(movieModel)
                                        navController.navigate(AppScreen.MovieScreen.route)
                                    }
                                }
                            }


                        }


                        else -> {}

                    }

            }

        }
    }
}

@Composable
fun MovieItem(model: MovieModel, onclick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                Color.LightGray,
                RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onclick()
            }
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "Title: ${model.title}",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "Year: ${model.year}",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        Row {
            Text(modifier = Modifier.padding(horizontal = 10.dp), text = "Rate: ")
            for (star in 1..model.rating) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(1.dp))
            }

        }
        Spacer(modifier = Modifier.size(10.dp))
    }

}





