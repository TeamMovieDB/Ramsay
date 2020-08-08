package com.example.ramsay.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ramsay.model.Customer

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM customer WHERE customerId=:customerId")
    fun getCustomer(customerId: Int?): Customer
}