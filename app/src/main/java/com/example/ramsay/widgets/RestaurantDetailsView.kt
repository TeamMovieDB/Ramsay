package com.example.ramsay.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ramsay.R
import com.example.ramsay.model.Restaurant

class RestaurantDetailsView : CardView {

    private lateinit var tvDescription: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvNumber: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvDeliveryTime: TextView
    private lateinit var tvPrice: TextView


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    fun setData(restaurant: Restaurant) {
        tvDescription.text = restaurant.description
        tvAddress.text = restaurant.address
        tvNumber.text = restaurant.phone
        tvRating.text = restaurant.likeCount
        tvDeliveryTime.text = restaurant.timeCount
        tvPrice.text = restaurant.averageCost
    }

    private fun init(context: Context) {
        ConstraintLayout.inflate(context, R.layout.restaurant_details_view, this)

        tvDescription = findViewById(R.id.tvDescription)
        tvAddress = findViewById(R.id.tvAddress)
        tvNumber = findViewById(R.id.tvNumber)
        tvRating = findViewById(R.id.tvRating)
        tvDeliveryTime = findViewById(R.id.tvDeliveryTime)
        tvPrice = findViewById(R.id.tvPrice)

    }

    private fun init(context: Context, attrs: AttributeSet) {
        init(context)
    }
}