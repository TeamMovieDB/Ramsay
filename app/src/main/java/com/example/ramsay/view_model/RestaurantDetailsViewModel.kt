package com.example.ramsay.view_model

import androidx.lifecycle.ViewModel
import com.example.ramsay.model.Dish
import com.example.ramsay.model.Restaurant
import com.example.ramsay.repository.RestaurantRepository

class RestaurantDetailsViewModel(private val restaurantRepository: RestaurantRepository) :
    ViewModel() {

    fun getRestaurantDescription(): Restaurant {
        return restaurantRepository.getRestaurant()
    }

    fun getMenu(): List<Dish> {
        return restaurantRepository.getMenu()
    }

    fun addToCart() {

    }

    fun changeOrderedDishAmount() {

    }
}
