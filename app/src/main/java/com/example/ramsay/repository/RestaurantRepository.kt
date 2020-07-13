package com.example.ramsay.repository

import android.util.Log
import com.example.ramsay.database.RestaurantDao
import com.example.ramsay.model.Customer
import com.example.ramsay.model.Dish
import com.example.ramsay.model.Restaurant

interface RestaurantRepository {
    fun insertRestaurants(restaurantsList: List<Restaurant>)
    fun insertMenu(menu: List<Dish>)
    fun insertRestaurant(restaurant: Restaurant)
    fun getRestaurantDetails(id: Int): Restaurant
    fun getMenu(restaurantId: Int): List<Dish>
    fun getRestaurants(): List<Restaurant>?
    fun deleteAll()
    fun getCustomer(): Customer
    fun setCustomer():Customer
}

class RestaurantRepositoryImpl(private val restaurantDao: RestaurantDao) : RestaurantRepository {
    override fun insertRestaurants(restaurantsList: List<Restaurant>) {
        restaurantDao.insertRestaurants(restaurantsList)
    }

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

    override fun getRestaurants(): List<Restaurant>? {
        return restaurantDao.getRestaurants()
    }

    override fun deleteAll() {
        restaurantDao.deleteAll()
    }

    override fun getCustomer(): Customer {
        TODO("Not yet implemented")
    }

    override fun setCustomer(): Customer {
        TODO("Not yet implemented")
    }
}
