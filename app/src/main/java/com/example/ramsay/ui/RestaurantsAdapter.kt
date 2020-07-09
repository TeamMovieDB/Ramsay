package com.example.ramsay.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.R
import com.example.ramsay.model.Restaurant
import kotlinx.android.synthetic.main.restaurant_item.view.*

class RestaurantsAdapter(private val restaurantClickListener: RestaurantItemClick?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var restaurants = mutableListOf<Restaurant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
        return RestaurantViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RestaurantViewHolder) {
            holder.bind(restaurants[position])
        }
    }

    fun setItems(items: MutableList<Restaurant>) {
        restaurants = items
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(restaurantData: Restaurant?) {
            val restaurantView = view.findViewById<View>(R.id.restaurant)
            restaurantView.restaurant.setRestaurantData(restaurantData)
            restaurantView.setOnClickListener {
                restaurantClickListener?.openDetails(adapterPosition, restaurantData)
            }
        }
    }

    interface RestaurantItemClick {
        fun openDetails(position: Int, item: Restaurant?)
    }
}