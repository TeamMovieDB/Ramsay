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

    @Query("SELECT * FROM dishes where restaurantId=:restaurantId")
    fun getMenu(restaurantId: Int?): List<Dish>

    @Query("SELECT * FROM restaurant where id=:id")
    fun getRestaurantDetails(id: Int): Restaurant
}
