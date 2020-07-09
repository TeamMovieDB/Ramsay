package com.example.ramsay.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ramsay.R
import com.example.ramsay.model.Restaurant
import com.squareup.picasso.Picasso

class RestaurantView : ConstraintLayout {
    private lateinit var ivLike: ImageView
    private lateinit var ivTime: ImageView
    private lateinit var ivCoin: ImageView
    private lateinit var ivRestaurant: ImageView
    private lateinit var tvLikeCounter: TextView
    private lateinit var tvTimeCounter: TextView
    private lateinit var tvCostCounter: TextView
    private lateinit var tvRestaurantName: TextView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    private fun init(context: Context) {
        LinearLayout.inflate(context, R.layout.restaurant, this)
        ivLike = findViewById(R.id.ivLike)
        ivTime = findViewById(R.id.ivTime)
        ivCoin = findViewById(R.id.ivCoin)
        tvLikeCounter = findViewById(R.id.tvLikeCounter)
        tvTimeCounter = findViewById(R.id.tvTimeCounter)
        tvCostCounter = findViewById(R.id.tvCostCounter)
        ivRestaurant = findViewById(R.id.ivRestaurant)
        tvRestaurantName = findViewById(R.id.tvRestaurantName)
    }

    fun setRestaurantData(restaurant: Restaurant?) {
        setLikeCount(restaurant?.likeCount)
        setTimeCount(restaurant?.timeCount)
        setCostCount(restaurant?.averageCost)
        setRestaurantImage(restaurant?.imagePath)
        setRestaurantName(restaurant?.name)
    }

    private fun setLikeCount(likeCount: String?) {
        tvLikeCounter.text = likeCount
    }

    private fun setTimeCount(timeCount: String?) {
        tvTimeCounter.text = timeCount
    }

    private fun setCostCount(costCount: String?) {
        tvCostCounter.text = costCount
    }

    private fun setRestaurantImage(imagePath: String?) {
        Picasso.get().load(imagePath).into(ivRestaurant)
    }

    private fun setRestaurantName(name: String?) {
        tvRestaurantName.text = name
    }
}