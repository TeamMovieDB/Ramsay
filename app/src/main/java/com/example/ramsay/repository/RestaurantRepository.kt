package com.example.ramsay.repository

import com.example.ramsay.database.RestaurantDao
import com.example.ramsay.model.Dish
import com.example.ramsay.model.Restaurant

interface RestaurantRepository {
    fun insertMenu(menu: List<Dish>)
    fun insertRestaurant(restaurant: Restaurant)
    fun getRestaurantDetails(id: Int): Restaurant
    fun getMenu(restaurantId: Int): List<Dish>
}

class RestaurantRepositoryImpl(private val restaurantDao: RestaurantDao) : RestaurantRepository {

    override fun insertMenu(menu: List<Dish>) {
        restaurantDao.insertMenu(menu)
    }

    override fun insertRestaurant(restaurant: Restaurant) {
        restaurantDao.insertRestaurant(restaurant)
    }

    override fun getRestaurantDetails(id: Int): Restaurant {
        return restaurantDao.getRestaurantDetails(id)
    }

    override fun getMenu(restaurantId: Int): List<Dish> {
        return restaurantDao.getMenu(restaurantId)
    }
}
