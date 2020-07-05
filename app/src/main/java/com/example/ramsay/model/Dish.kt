package com.example.ramsay.model

data class Dish(
    val id: Int,
    val restaurantId: Int? = 0,
    val categoryId: Int? = 0,
    val title: String?,
    val price: Int?,
    val description: String?,
    val ingredients: String? = null,
    val image: String? = null
)
