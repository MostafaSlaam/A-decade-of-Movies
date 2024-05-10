package com.task.a_decade_of_movies.features.movie_details.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.task.a_decade_of_movies.R
import com.task.a_decade_of_movies.core.DataStatePaging
import com.task.a_decade_of_movies.features.home.presentation.view.MovieItem
import com.task.a_decade_of_movies.features.home.presentation.viewmodel.HomeEvent
import com.task.a_decade_of_movies.features.main.MainViewModel
import com.task.a_decade_of_movies.features.movie_details.presentation.viewmodel.MovieDetailsViewModel
import com.task.a_decade_of_movies.features.movie_details.presentation.viewmodel.MovieEvent

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.onEvent(MovieEvent.LoadImages)
    })
    Content(
        navController = navController,
        viewModel = viewModel,
        mainViewModel = mainViewModel,

        )


}

@Composable
private fun Content(
    navController: NavController,
    viewModel: MovieDetailsViewModel,
    mainViewModel: MainViewModel,


    ) {

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            MovieItem(model = viewModel.formState.currentMovie!!) {

            }
            Spacer(modifier = Modifier.size(20.dp))
            ImagesContent(
                viewModel = viewModel,

                )
        }
    }
}


@Composable
fun ImagesContent(

    viewModel: MovieDetailsViewModel,

    ) {

    val lazyListState = rememberLazyGridState()


    val isLastItemView by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
                    &&
                    viewModel.movieImagesState.value !is DataStatePaging.NextLoading
                    &&
                    viewModel.movieImagesState.value !is DataStatePaging.Loading

                    &&
                    viewModel.formState.isNext
        }
    }
    if (isLastItemView) {
        viewModel.onEvent(MovieEvent.LoadImages)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = lazyListState,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            when (viewModel.movieImagesState.value) {
                is DataStatePaging.Failure -> {
//                    item {
//                        EmptyData(
//                            modifier = Modifier.fillParentMaxSize(),
//                            painter = painterResource(id = R.drawable.ic_no_internet),
//                            text = stringResource(id = R.string.strEmptyData)
//                        )
//                    }
                }

                is DataStatePaging.Loading -> {
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        Column {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center),
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        }
                    }
                }

                is DataStatePaging.Success -> {
                    if (viewModel.images.isEmpty()) {
                        item {

                        }
                    }


                }


                else -> {}

            }


            items(viewModel.images.size) {
                AsyncImage(
                    model = "https://farm${viewModel.images[it].farm}.static.flickr.com/${viewModel.images[it].server}/${viewModel.images[it].id}_${viewModel.images[it].secret}.jpg",
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(id = R.drawable.placeholder)
                )
            }

            if (viewModel.movieImagesState.value is DataStatePaging.NextLoading) {
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.TopCenter),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }

        }

    }

}











