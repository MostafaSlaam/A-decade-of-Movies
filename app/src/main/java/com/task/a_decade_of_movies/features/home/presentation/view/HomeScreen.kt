package com.ea.eaapp.features.restaurant.presentation.restaurants.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.ea.eaapp.R
import com.ea.eaapp.core.app.AppPreferences
import com.ea.eaapp.core.app.Constants
import com.ea.eaapp.core.component.ComposableLifecycle
import com.ea.eaapp.core.component.appbar.CustomAppBarFirstFigure
import com.ea.eaapp.core.component.button.toggle.CustomToggleButton
import com.ea.eaapp.core.component.errorScreen.ErrorScreen
import com.ea.eaapp.core.component.item.restaurant.ItemRestaurantCard
import com.ea.eaapp.core.component.item.restaurant.ItemRestaurantList
import com.ea.eaapp.core.component.shimmer.AnimatedShimmer
import com.ea.eaapp.core.component.shimmer.ShimmerItemRestaurant
import com.ea.eaapp.core.rout.AppScreen
import com.ea.eaapp.core.theme.*
import com.ea.eaapp.features.common.LocationUtil
import com.ea.eaapp.features.common.PermissionHandler
import com.ea.eaapp.features.home.presentation.home.viewmodel.HomeEvent
import com.ea.eaapp.features.main.MainEvent
import com.ea.eaapp.features.main.MainViewModel
import com.ea.eaapp.features.restaurant.domain.model.FilterType
import com.ea.eaapp.features.restaurant.domain.model.Restaurant
import com.ea.eaapp.features.restaurant.presentation.filter.view.FilterBottomSheet
import com.ea.eaapp.features.restaurant.presentation.restaurants.viewmodel.RestaurantsEvent
import com.ea.eaapp.features.restaurant.presentation.restaurants.viewmodel.RestaurantsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.CoroutineScope

@SuppressLint("SuspiciousIndentation")
@Composable
fun RestaurantsScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    viewModel: RestaurantsViewModel,
    paddingValues: PaddingValues,
    scope: CoroutineScope,
    restaurants: LazyPagingItems<Restaurant>,
    filterType: String
) {
    ComposableLifecycle { source, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {


            }

            Lifecycle.Event.ON_START -> {
            }

            Lifecycle.Event.ON_RESUME -> {
                viewModel.onEvent(RestaurantsEvent.ModeChanged(AppPreferences.getShowWay()))

            }

            Lifecycle.Event.ON_PAUSE -> {
            }

            Lifecycle.Event.ON_STOP -> {
            }

            Lifecycle.Event.ON_DESTROY -> {
            }

            else -> {}
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        Log.e(
            "TAG",
            "RestaurantsScreen: filterType = $filterType  Constants.FREE_DELIVERY = ${Constants.FREE_DELIVERY}",
        )

        when (filterType) {
            "1" -> {
                viewModel.onEvent(

                    RestaurantsEvent.FilterTypeChange(
                        FilterType(
                            pageNumber = 1,
                            userLat = Constants.USER_LAT,
                            userLong = Constants.USER_LON,
                            freeDelivery = false,
                            nearest = false,
                            topRated = true,
                            noMinimumOrder = false,
                            sortBy = 0,
                        )
                    )
                )


                Log.e(
                    "TAG",
                    "RestaurantsScreen:  @TOP_RATED ${viewModel.formState.filterType.userLat}  Constants.USER_LAT = ${Constants.USER_LAT} ",
                )
            }

            "2" -> {
                viewModel.onEvent(
                    RestaurantsEvent.FilterTypeChange(
                        FilterType(
                            pageNumber = 1,
                            userLat = Constants.USER_LAT,
                            userLong = Constants.USER_LON,
                            freeDelivery = false,
                            nearest = true,
                            topRated = false,
                            noMinimumOrder = false,
                            sortBy = 0,
                        )
                    )
                )

            }

            "3" -> {

                Log.e("TAG", "RestaurantsScreen:  @FREE_DELIVERY")
                viewModel.onEvent(
                    RestaurantsEvent.FilterTypeChange(
                        FilterType(
                            pageNumber = 1,
                            userLat = Constants.USER_LAT,
                            userLong = Constants.USER_LON,
                            freeDelivery = true,
                            nearest = false,
                            topRated = false,
                            noMinimumOrder = false,
                            sortBy = 0,
                        )
                    )
                )

                Log.e(
                    "TAG",
                    "RestaurantsScreen:  @FREE_DELIVERY ${viewModel.formState.filterType.freeDelivery}",
                )


            }

            else -> {

                viewModel.onEvent(
                    RestaurantsEvent.FilterTypeChange(
                        FilterType(
                            pageNumber = 1,
                            userLat = Constants.USER_LAT,
                            userLong = Constants.USER_LON,
                            freeDelivery = Constants.FREE_DELIVERY,
                            nearest = Constants.NEAREST,
                            topRated = Constants.TOP_RATED,
                            noMinimumOrder = false,
                            sortBy = 0,
                        )
                    )
                )






//                viewModel.onEvent(RestaurantsEvent.GetRestaurants)

            }
        }
    })

    val state = rememberLazyListState()
    mainViewModel.onEvent(MainEvent.VisibleBottomNavigationChange(true))



    if (viewModel.formState.isOpenBottomSheet) {
        FilterBottomSheet(
            filterType = viewModel.formState.filterType,
            setShowBottomSheet = {
                viewModel.onEvent(RestaurantsEvent.OpenBottomSheetChange(it))
            },
            returnValue = {
                Log.d("TAG", "RestaurantsScreen: $it")
                viewModel.onEvent(RestaurantsEvent.FilterTypeChange(it ?: FilterType()))
            }
        )
    }


    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //region Appbar

            CustomAppBarFirstFigure(
                title = stringResource(id = R.string.strRestaurants),
                backPressed = {
                    navController.popBackStack()
                },
                firstOnClick = {
                    viewModel.onEvent(RestaurantsEvent.OpenBottomSheetChange(true))
                },
                secondOnClick = {
                    navController.navigate(AppScreen.SearchScreen.route + "/${0}")
                },
                thirdOnClick = {
                    if (AppPreferences.getCurrentUser()==null)
                        mainViewModel.onEvent(MainEvent.OpenLockDialog(true))
                    else
                    navController.navigate(AppScreen.NotificationsScreen.route)
                }
            )

            //endregion

            SwipeRefresh(
                state = SwipeRefreshState(viewModel.formState.isRefresh),
                onRefresh = {
                    Log.e("TAG", "RestaurantsScreen:filterType=$filterType ")
                    viewModel.onEvent(RestaurantsEvent.RefreshData(filterType))
                },
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = trigger,
                        scale = true,
                        backgroundColor = MaterialTheme.colors.surface,
                        contentColor = MaterialTheme.colors.primary,
                    )
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Log.e(
                        "TAG",
                        "RestaurantsScreen: viewModel.formState.mode = ${viewModel.formState.mode}",
                    )
                    CustomToggleButton(
                        state = viewModel.formState.mode,
                        onClickCard = {
                            viewModel.onEvent(RestaurantsEvent.ModeChanged(1))
//                            AppPreferences.storeShowWay(1)
                        },
                        onClickList = {
                            viewModel.onEvent(RestaurantsEvent.ModeChanged(0))
//                            AppPreferences.storeShowWay(0)

                        },
                        modifier = Modifier.padding(
                            start = AppMargin.dim16,
                            end = AppMargin.dim16,
                            bottom = AppMargin.dim16,
                            top = AppMargin.dim16
                        )
                    )
                    ListContent(restaurants, navController, viewModel, paddingValues, state)

                    Spacer(modifier = Modifier.padding(AppMargin.dim50))

                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun ListContent(
    restaurants: LazyPagingItems<Restaurant>,
    navController: NavController,
    viewModel: RestaurantsViewModel,
    paddingValues: PaddingValues,
    state: LazyListState
) {

    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize(),
    ) {
        when (restaurants.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                viewModel.onEvent(RestaurantsEvent.StopRefresh)
                item {
                    ErrorScreen(
                        modifier = Modifier.fillParentMaxSize()
                    )
                }
            }

            is LoadState.Loading -> {
                item {
                    Column {
                        repeat(5) {
                            AnimatedShimmer(
                                shimmerItem = {
                                    ShimmerItemRestaurant(brush = it)
                                }
                            )
                        }
                    }
                }
            }

            else -> {
                viewModel.onEvent(RestaurantsEvent.StopRefresh)
                if (restaurants.itemCount == 0) {
                    item {
                        com.ea.eaapp.core.component.EmptyData(
                            modifier = Modifier.fillParentMaxSize(),
                            painter = painterResource(id = R.drawable.empty_restaurant),
                            text = stringResource(
                                id = R.string.empty
                            ),
                            text2 = stringResource(id = R.string.emptyRestaurants)
                        )
                    }
                }

                items(
                    count = restaurants.itemCount,
                    key = { restaurants[it]!!.id }
                ) { x ->
                    if (viewModel.formState.mode == 0) {
                        ItemRestaurantCard(
                            restaurant = restaurants[x]!!,
                            onClick = {
                                navController.navigate(AppScreen.RestaurantDetailsScreen.route + "/${it}")
                            },
                            favoriteOnClick = {
                                viewModel.onEvent(RestaurantsEvent.SetFavoriteRestaurant(restaurants[x]!!.id))
                            }
                        )
                    } else {
                        ItemRestaurantList(
                            restaurants[x]!!,
                            onClick = {
                                navController.navigate(AppScreen.RestaurantDetailsScreen.route + "/${it}")
                            },
                            favoriteOnClick = {
                                viewModel.onEvent(RestaurantsEvent.SetFavoriteRestaurant(restaurants[x]!!.id))
                            }
                        )
                    }
                }
            }
        }

        when (restaurants.loadState.append) { // Pagination
            is LoadState.Error -> {
            }

            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }

            else -> {}
        }

        if (restaurants.itemCount != 0) {
            item {
                Spacer(modifier = Modifier.padding(paddingValues))
            }
        }

    }

}

@Composable
private fun EmptyData(
    modifier: Modifier,
    painter: Painter,
    text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(AppSize.size200)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppMargin.dim20)
        )
        Spacer(modifier = Modifier.padding(AppMargin.dim20))
    }
}

