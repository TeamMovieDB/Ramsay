package com.example.ramsay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ramsay.model.Customer
import com.example.ramsay.model.Dish
import com.example.ramsay.model.Restaurant

@Database(entities = [Restaurant::class, Dish::class, Customer::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
    abstract fun customerDao(): CustomerDao

    companion object {
        var database: RestaurantDatabase? = null
        private const val databaseName: String = "restaurant_database5.db"
        fun getDatabase(context: Context): RestaurantDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    databaseName
                ).build()
            }
            return database!!
        }
    }
}