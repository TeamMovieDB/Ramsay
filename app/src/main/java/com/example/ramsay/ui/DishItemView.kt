package com.example.ramsay.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ramsay.R
import com.example.ramsay.model.Dish

class DishItemView : CardView {

    private lateinit var ivPhoto: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvPrice: TextView
    private lateinit var ivAdd: ImageView
    private lateinit var tvAmount: TextView
    private lateinit var ivRemove: ImageView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    fun setData(dish: Dish?) {
        //Picasso.get().load(dish.image).into(ivPhoto)
        tvTitle.text = dish?.title
        tvDescription.text = dish?.description
        tvPrice.text = context.getString(R.string.price, dish?.price)
        tvAmount.text = 0.toString()
    }

    private fun setListeners() {
        var amount = Integer.parseInt(tvAmount.text.toString())

        ivAdd.setOnClickListener {
            amount++
            tvAmount.text = (amount).toString()
            tvAmount.visibility = View.VISIBLE
            ivRemove.visibility = View.VISIBLE
        }

        ivRemove.setOnClickListener {
            if (amount > 1) {
                amount--
                tvAmount.text = (amount).toString()
            } else {
                tvAmount.visibility = View.GONE
                ivRemove.visibility = View.GONE
                amount = 0
            }
        }
    }

    private fun init(context: Context) {
        ConstraintLayout.inflate(context, R.layout.dish_item, this)

        ivPhoto = findViewById(R.id.ivPhoto)
        tvTitle = findViewById(R.id.tvTitle)
        tvDescription = findViewById(R.id.tvDescription)
        tvPrice = findViewById(R.id.tvPrice)
        ivAdd = findViewById(R.id.ivAdd)
        tvAmount = findViewById(R.id.tvAmount)
        ivRemove = findViewById(R.id.ivRemove)

        tvAmount.text = 0.toString()
        setListeners()
    }

    private fun init(context: Context, attrs: AttributeSet) {
        init(context)
    }

}