package com.example.ramsay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ramsay.model.Customer


@Database(entities = [Customer::class], version = 1)
abstract class CustomerDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao

    companion object {
        var database: CustomerDatabase? = null
        private const val databaseName: String = "customer_database.db"
        fun getDatabase(context: Context): CustomerDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerDatabase::class.java,
                    databaseName
                ).build()
            }
            return database!!
        }
    }
}