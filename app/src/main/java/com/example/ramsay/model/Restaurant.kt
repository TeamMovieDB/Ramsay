package com.example.ramsay.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "restaurant")
data class Restaurant(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val phone: String,
    val imagePath: String,
    val likeCount: String,
    val timeCount: String,
    val averageCost: String
): Serializable