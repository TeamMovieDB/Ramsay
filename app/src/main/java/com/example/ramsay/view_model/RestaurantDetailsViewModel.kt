package com.example.ramsay.view_model

import androidx.lifecycle.MutableLiveData
import com.example.ramsay.model.Dish
import com.example.ramsay.model.Restaurant
import com.example.ramsay.repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RestaurantDetailsViewModel(private val restaurantRepository: RestaurantRepository) :
    BaseViewModel() {

    val liveData = MutableLiveData<State>()

    init {
        fillDatabase()
    }

    private fun fillDatabase() {
        val menu: MutableList<Dish> = mutableListOf()

        for (i in 1..10) {

            val dish = Dish(
                id = i,
                title = "Big Burger",
                description = "A hamburger (also burger for short) is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun. The patty may be pan fried, grilled, smoked[1] or flame broiled. Hamburgers are often served with cheese, lettuce, tomato, onion, pickles, bacon, or chiles; condiments such as ketchup, mustard, mayonnaise, relish",
                price = 1200
            )
            menu.add(dish)
        }

        launch {
            withContext(Dispatchers.IO) {
                restaurantRepository.insertMenu(menu)
            }
            liveData.value = State.DatabaseFilled
        }
    }

    fun getRestaurantDescription(id: Int) {
        launch {
            val restaurant =
                withContext(Dispatchers.IO) {
                    return@withContext restaurantRepository.getRestaurantDetails(id)
                }
            liveData.value = State.RestaurantDetails(restaurant)
            liveData.value = State.HideLoading
        }
    }

    fun getMenu(restaurantId: Int = 1) {
        launch {
            val menu = withContext(Dispatchers.IO) {
                return@withContext restaurantRepository.getMenu(restaurantId)
            }
            liveData.value = State.Menu(menu)
            liveData.value = State.HideLoading
        }
    }

    fun addToCart() {}
    fun changeOrderedDishAmount() {}

    sealed class State {
        object DatabaseFilled : State()
        object HideLoading : State()
        data class Menu(val menu: List<Dish>?) : State()
        data class RestaurantDetails(val restaurant: Restaurant?) : State()
    }
}
