package com.example.ramsay.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.model.Dish
import com.example.ramsay.widgets.AddToCartClickListener
import com.example.ramsay.widgets.DishItemView

class MenuAdapter(
    private val itemClickListener: RecyclerViewItemClick? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var menu: List<Dish>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = DishItemView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int = menu?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MenuViewHolder)
            holder.bind(menu?.get(position))
    }

    fun setMenu(menu: List<Dish>?) {
        this.menu = menu
    }

    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val dishItemView: DishItemView = view as DishItemView

        fun bind(dish: Dish?) {
            dishItemView.setData(dish)

            dishItemView.setOnClickListener {
                if (dish != null) {
                    itemClickListener?.itemClick(adapterPosition, dish)
                }
            }

            val addToCartClickListener: AddToCartClickListener = object :
                AddToCartClickListener {
                override fun addToCart(item: Dish?) {
                    if (dish != null) {
                        itemClickListener?.addToCartClick(dish)
                    }
                }

                override fun updateAmount(item: Dish?, amount: Int) {
                    if (item != null) {
                        itemClickListener?.updateItemAmount(item, amount)
                    }
                }
            }
            dishItemView.setAddToCartListener(addToCartClickListener)

        }
    }

    interface RecyclerViewItemClick {
        fun itemClick(position: Int, item: Dish)
        fun addToCartClick(item: Dish)
        fun updateItemAmount(item: Dish, amount: Int)
    }
}