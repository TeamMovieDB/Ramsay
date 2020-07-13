package com.example.ramsay.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ramsay.model.Dish
import com.example.ramsay.model.Restaurant

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenu(menu: List<Dish>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurant: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurants(restaurants: List<Restaurant>)

    @Query("SELECT * FROM dishes where restaurantId=:restaurantId")
    fun getMenu(restaurantId: Int?): List<Dish>

    @Query("SELECT * FROM restaurant where id=:id")
    fun getRestaurantDetails(id: Int): Restaurant

    @Query("SELECT * FROM restaurant")
    fun getRestaurants(): List<Restaurant>

    @Query("DELETE FROM restaurant")
    fun deleteAll()

    @Query("UPDATE dishes SET isInCart=1 WHERE id=:id")
    fun insertItemToCart(id: Int?)

    @Query("SELECT * FROM dishes WHERE isInCart=1")
    fun getBasketItems(): List<Dish>?

    @Query("DELETE FROM dishes WHERE isInCart=1")
    fun clearBasket()
}

