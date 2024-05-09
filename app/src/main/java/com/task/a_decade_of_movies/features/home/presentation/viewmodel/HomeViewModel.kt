package com.ea.eaapp.features.restaurant.presentation.restaurant_details.viewmodel

import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ea.eaapp.core.app.AppPreferences
import com.ea.eaapp.core.app.Constants
import com.ea.eaapp.features.address.domain.model.AddressName
import com.ea.eaapp.features.common.state_render.DataState
import com.ea.eaapp.features.locationMap.domain.GetRoutingUseCase
import com.ea.eaapp.features.locationMap.domain.model.Route
import com.ea.eaapp.features.locationMap.domain.model.RoutingRequest
import com.ea.eaapp.features.restaurant.domain.model.*
import com.ea.eaapp.features.restaurant.domain.usecase.GetRestaurantCategoriesUseCase
import com.ea.eaapp.features.restaurant.domain.usecase.GetRestaurantDetailsUseCase
import com.ea.eaapp.features.restaurant.domain.usecase.GetRestaurantProductsUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(
    private val getRestaurantByIdUserCase: GetRestaurantDetailsUseCase,
//    private val getLocationName: GetLocationNameUseCase,
    private val getRestaurantCategoriesUseCase: GetRestaurantCategoriesUseCase,
    private val getRestaurantProductsUseCase: GetRestaurantProductsUseCase,
    private val getRoutingUseCase: GetRoutingUseCase
) : ViewModel() {

    var formState by mutableStateOf(RestaurantDetailsState())

    var observeScrollerLazyColumn by mutableIntStateOf(0)

//    val observeScrollerLazyColumn = MutableLiveData(0)

    private val _restaurantDetailsState = mutableStateOf<DataState<Restaurant>>(DataState.Init)
    val restaurantDetailsState: State<DataState<Restaurant>> = _restaurantDetailsState

    private val _restaurantLocationState = mutableStateOf<DataState<String>>(DataState.Init)
    val restaurantLocationState: State<DataState<String>> = _restaurantLocationState

    //    private val _restaurantCategoriesState =
//        mutableStateOf<DataState<List<RestaurantCategory>>>(DataState.Init)
//    val restaurantCategoriesState: State<DataState<List<RestaurantCategory>>> =
//        _restaurantCategoriesState
    var restaurantCategories = mutableStateListOf<RestaurantCategory>()
    var currency by mutableStateOf("")
    var restaurantMeals= mutableStateListOf<RestaurantMeal>()

//    private val _restaurantProductsState =
//        mutableStateOf<DataState<RestaurantMeals>>(DataState.Init)
//    val restaurantProductsState: State<DataState<RestaurantMeals>> = _restaurantProductsState

    private val _restaurantCategoriesState =
        mutableStateOf<DataState<List<RestaurantCategory>>>(DataState.Init)
    val restaurantCategoriesState: State<DataState<List<RestaurantCategory>>> =
        _restaurantCategoriesState

    private val _restaurantProductsState =
        mutableStateOf<DataState<RestaurantMeals>>(DataState.Init)
    val restaurantProductsState: State<DataState<RestaurantMeals>> = _restaurantProductsState

    var placeName by mutableStateOf("")
    var fullPlaceName by mutableStateOf("")
    var geocoder: Geocoder? = null

    private val _routState = mutableStateOf<DataState<Route>>(DataState.Init)
    val routState: State<DataState<Route>> = _routState


    fun onEvent(event: RestaurantDetailsEvent) {
        viewModelScope.launch {
            when (event) {
                is RestaurantDetailsEvent.DataChange -> {
                    formState = formState.copy(latLng = event.latLng)

                }

                is RestaurantDetailsEvent.GetRestaurantDetails -> {
                    getRestaurantDetails(event.restaurantId)
                }

                is RestaurantDetailsEvent.GetRestaurantCategories -> {
                    getRestaurantCategories(event.restaurantId)
                }

                is RestaurantDetailsEvent.GetRestaurantProducts -> {
                    getRestaurantProducts(event.restaurantId)
                }

                is RestaurantDetailsEvent.OpenWorkTimeBottomSheetChange -> {
                    formState =
                        formState.copy(isOpenWorkTimeBottomSheet = event.isOpenWorkTimeBottomSheet)
                }

                is RestaurantDetailsEvent.OpenReviewsBottomSheetChange -> {
                    formState =
                        formState.copy(isOpenReviewsBottomSheet = event.isOpenReviewsBottomSheet)
                }

                is RestaurantDetailsEvent.OpenDrinkBottomSheetChange -> {
                    formState =
                        formState.copy(isOpenDrinkBottomSheet = event.isOpenDrinkBottomSheet)

                }

                is RestaurantDetailsEvent.ModeChanged -> {
                    formState = formState.copy(mode = event.mode)
                    AppPreferences.storeShowWay(event.mode)

                }

                is RestaurantDetailsEvent.OpenLocationBottomSheetChange -> {
                    formState =
                        formState.copy(openBottomSheetLocation = event.isOpenLocationBottomSheet)


                }

                is RestaurantDetailsEvent.SetProductIDForDrink -> {
                    formState =
                        formState.copy(productId = event.productId)
                }

                is RestaurantDetailsEvent.CheckCart -> {
                    formState = formState.copy(cartEmpty = AppPreferences.getCartItems().isEmpty())
                }

                is RestaurantDetailsEvent.GetRouting -> {
                    startRouting(RoutingRequest(event.orgin, event.dest))
                }

                is RestaurantDetailsEvent.GetRestaurantPlaceName -> {
                    getPlaceName(event.latLng)
                }
            }
        }
    }


    private suspend fun getRestaurantDetails(restaurantId: String) {

        getRestaurantByIdUserCase.execute(
            RestaurantRequest(
                restaurantId = restaurantId,
                latLng = formState.latLng
            )
        ).collect {
            _restaurantDetailsState.value = it
            if (it is DataState.Success) {
                formState=formState.copy(currency=it.data.currency , minimumOrder = it.data.minimumOrderPrice)
            }



        }
    }

    private fun getPlaceName(latLng: LatLng) {


        viewModelScope.launch((Dispatchers.IO)) {
            try {
                geocoder?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        geocoder!!.getFromLocation(
                            latLng.latitude,
                            latLng.longitude,
                            1,
                            object : Geocoder.GeocodeListener {
                                override fun onGeocode(addresses: MutableList<Address>) {

                                    if (addresses.size > 0) {
//                                        fullPlaceName = addresses[0].getAddressLine(0)
                                        placeName = addresses[0].subAdminArea

                                    }
                                }

                                override fun onError(errorMessage: String?) {
                                    super.onError(errorMessage)

                                }

                            })
                    } else {
                        val address = geocoder!!.getFromLocation(
                            latLng.latitude,
                            latLng.longitude, 1
                        )
                        if (address!!.size > 0) {
//                            fullPlaceName = address[0].getAddressLine(0)
                            placeName = address[0].subAdminArea


                        }

                    }
                }

                Log.e("TAG", "storeCurrentAddress: $placeName")
//                Log.e("TAG", "storeCurrentAddress: ${address[0].subAdminArea}")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    private suspend fun getRestaurantCategories(restaurantId: String) {
        getRestaurantCategoriesUseCase.execute(restaurantId).collect {
            if (it is DataState.Success)
                restaurantCategories = it.data.toMutableStateList()
//            _restaurantCategoriesState.value = it

        }
    }

    private suspend fun getRestaurantProducts(restaurantId: String) {
        getRestaurantProductsUseCase.execute(restaurantId).collect {
            if (it is DataState.Success){
                currency=it.data.currency
                restaurantMeals=it.data.meals.toMutableStateList()
            }
//            _restaurantProductsState.value = it

        }
    }

    fun filterCategories(
        categories: List<RestaurantCategory>,
        meals: List<RestaurantMeal>
    ): List<RestaurantCategory> {
        var mList = HashMap<String, RestaurantCategory>()
        categories.forEach { cat ->
            meals.forEach { meal ->

                if (cat.id == meal.categoryId && meal.products.isNotEmpty()) {
//                    Log.e("TAG", "meal.products.isNotEmpty: ${meal.products.size}")
                    if (!mList.containsKey(cat.id)) {

                        mList[cat.id] = cat
                    }
//                    Log.e("TAG", "!mList.containsKey(cat.id: ${meal.products.size}")
                }

            }
        }
        Log.e("TAG", "filterCategories: ${mList.size}")
        return mList.values.reversed().toList()
    }

    private suspend fun startRouting(request: RoutingRequest) {
        getRoutingUseCase.execute(request).collect {
            _routState.value = it
        }
    }
}


sealed class RestaurantDetailsEvent {
    data class OpenLocationBottomSheetChange(
        val isOpenLocationBottomSheet: Boolean
    ) :
        RestaurantDetailsEvent()

    data class OpenWorkTimeBottomSheetChange(val isOpenWorkTimeBottomSheet: Boolean) :
        RestaurantDetailsEvent()

    data class OpenReviewsBottomSheetChange(val isOpenReviewsBottomSheet: Boolean) :
        RestaurantDetailsEvent()

    data class OpenDrinkBottomSheetChange(val isOpenDrinkBottomSheet: Boolean) :
        RestaurantDetailsEvent()

    data class GetRestaurantDetails(val restaurantId: String) : RestaurantDetailsEvent()
    data class GetRestaurantCategories(val restaurantId: String) : RestaurantDetailsEvent()
    data class GetRestaurantProducts(val restaurantId: String) : RestaurantDetailsEvent()
    data class DataChange(val latLng: LatLng) : RestaurantDetailsEvent()

    data class ModeChanged(val mode: Int) : RestaurantDetailsEvent()
    data class SetProductIDForDrink(val productId: String) : RestaurantDetailsEvent()
    object CheckCart : RestaurantDetailsEvent()
    data class GetRestaurantPlaceName(val latLng: LatLng) : RestaurantDetailsEvent()

    data class GetRouting(val orgin: LatLng, val dest: LatLng) : RestaurantDetailsEvent()

}

data class RestaurantDetailsState(
    val latLng: LatLng = LatLng(0.0, 0.0),
    val isOpenWorkTimeBottomSheet: Boolean = false,
    val isOpenReviewsBottomSheet: Boolean = false,
    val isOpenDrinkBottomSheet: Boolean = false,
    val productId: String = "",
    val currency: String = "",
    val minimumOrder: Int = 0,
    val mode: Int = AppPreferences.getShowWay(),
    var openBottomSheetLocation: Boolean = false,
    var cartEmpty: Boolean = true
)

