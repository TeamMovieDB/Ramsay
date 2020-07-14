package com.example.ramsay.repository

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
    fun updateDishAmount(item: Dish, amount: Int)

    fun getCartItems(): List<Dish>?
    fun addToCart(id: Int?)
    fun clearCart()
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

    override fun getCartItems(): List<Dish>? {
        return restaurantDao.getBasketItems()
    }

    override fun clearCart() {
        restaurantDao.clearBasket()
    }

    override fun addToCart(id: Int?) {
        restaurantDao.insertItemToCart(id)
    }

    override fun updateDishAmount(item: Dish, amount: Int) {
        restaurantDao.updateDishAmount(item.id, amount)
    }

    override fun getCustomer(): Customer {
        TODO("Not yet implemented")
    }

    override fun setCustomer(): Customer {
        TODO("Not yet implemented")
    }
}
