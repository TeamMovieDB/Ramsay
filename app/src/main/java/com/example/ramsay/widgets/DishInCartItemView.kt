package com.example.ramsay.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ramsay.R
import com.example.ramsay.model.Dish


class DishInCartItemView : CardView {

    private lateinit var tvTitle: TextView
    private lateinit var tvPrice: TextView
    private lateinit var ivAdd: ImageView
    private lateinit var tvAmount: TextView
    private lateinit var ivRemove: ImageView
    private var addToCartClick: AddToCartClickListener? = null

    private var item: Dish? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    fun setData(dish: Dish?) {
        item = dish
        tvTitle.text = dish?.title
        tvPrice.text = context.getString(R.string.price, dish?.price)
        tvAmount.text = (dish?.amount ?: 0).toString()
    }

    fun setAddToCartListener(listener: AddToCartClickListener) {
        addToCartClick = listener
    }

    private fun setListeners() {

        ivAdd.setOnClickListener {
            var amount = Integer.parseInt(tvAmount.text.toString())
            amount++
            addToCartClick?.updateAmount(item, amount)
            tvAmount.text = (amount).toString()
            tvAmount.visibility = View.VISIBLE
            ivRemove.visibility = View.VISIBLE
        }

        ivRemove.setOnClickListener {
            var amount = Integer.parseInt(tvAmount.text.toString())

            if (amount > 1) {
                amount--
                tvAmount.text = (amount).toString()
            } else {
                tvAmount.visibility = View.GONE
                ivRemove.visibility = View.GONE
                amount = 0
            }
            addToCartClick?.updateAmount(item, amount)
        }
    }


    private fun init(context: Context) {
        ConstraintLayout.inflate(context, R.layout.dish_in_cart_item, this)
        tvTitle = findViewById(R.id.tvTitle)
        tvPrice = findViewById(R.id.tvPrice)
        ivAdd = findViewById(R.id.ivAdd)
        tvAmount = findViewById(R.id.tvAmount)
        ivRemove = findViewById(R.id.ivRemove)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        init(context)
    }

}
