package com.example.ramsay.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ramsay.model.Dish
import com.example.ramsay.repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BasketViewModel(private val restaurantRepository: RestaurantRepository) : BaseViewModel() {

    val liveData = MutableLiveData<State>()

    fun getItems() {
        launch {
            val items = withContext(Dispatchers.IO) {
                restaurantRepository.getCartItems()
            }
            liveData.value = State.Items(items)
            Log.d("testt", items.toString())
        }
    }

    fun insertItem(item: Dish?) {
        if (item != null) {
            launch {
                withContext(Dispatchers.IO) {
                    restaurantRepository.addToCart(item.id)
                }
                liveData.value = State.Inserted(item)
            }
        }
    }

    sealed class State {
        data class Items(val items: List<Dish>?) : State()
        data class Inserted(val item: Dish) : State()
    }
}