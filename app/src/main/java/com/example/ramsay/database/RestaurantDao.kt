package com.example.ramsay.database

import android.util.Log
import androidx.room.*
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
}
