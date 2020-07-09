package com.example.ramsay.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ramsay.model.Restaurant
import com.example.ramsay.repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestaurantViewModel(
    private var restaurantRepository: RestaurantRepository
) : BaseViewModel() {
    val liveData = MutableLiveData<State>()

    init {
        setRestaurants()
    }

    private fun deleteAll() {
        launch {
            withContext(Dispatchers.IO) {
                restaurantRepository.deleteAll()
            }
        }
    }

    fun getRestaurants() {
        launch {
            val restaurantList = withContext(Dispatchers.IO) {
                return@withContext restaurantRepository.getRestaurants()
            }
            liveData.value = State.RestaurantList(restaurantList)
        }
    }

    private fun setRestaurants() {
        val restaurantList = mutableListOf<Restaurant>()
        for (i in 1..10) {
            val restaurant = Restaurant(
                i, "Bahandi", "The best restaurant with tasty burgers",
                "Almaty, Tole bi street, 50",
                "+77077881506", "lol", "98%", "25min", "1000T"
            )
            restaurantList.add(restaurant)
        }
        launch {
            withContext(Dispatchers.IO) {
                restaurantRepository.insertRestaurants(restaurantList)
            }
        }
        liveData.value = State.DBfilled
    }

    sealed class State {
        object DBfilled : State()
        data class RestaurantList(val restaurantResult: List<Restaurant>?) : State()
    }
}