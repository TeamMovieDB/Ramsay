package com.example.ramsay.model

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val phone: String,
    val imagePath: String,
    val likeCount: String,
    val timeCount: String,
    val averageCost: String
)