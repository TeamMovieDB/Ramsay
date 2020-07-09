package com.example.ramsay.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class Dish(
    @PrimaryKey
    val id: Int,
    val restaurantId: Int? = 1,
    val categoryId: Int? = 1,
    val title: String?,
    val price: Int?,
    val description: String?,
    val ingredients: String? = null,
    val image: String? = null
)
