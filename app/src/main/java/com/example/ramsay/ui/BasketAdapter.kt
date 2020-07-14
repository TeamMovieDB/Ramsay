package com.example.ramsay.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.model.Dish
import com.example.ramsay.widgets.AddToCartClickListener
import com.example.ramsay.widgets.DishInCartItemView

class BasketAdapter(
    private val itemClickListener: ItemClick? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Dish>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = DishInCartItemView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return CartItemsViewHolder(view)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CartItemsViewHolder)
            holder.bind(items?.get(position))
    }

    fun setCartItems(items: List<Dish>?) {
        this.items = items as MutableList<Dish>?
        notifyDataSetChanged()
    }

    fun insertItem(item: Dish) {
        items?.add(item)
        items?.size?.minus(1)?.let { notifyItemInserted(it) }
    }

    fun removeItems() {
        items?.clear()
        notifyDataSetChanged()
    }

    inner class CartItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val cartItemView: DishInCartItemView = view as DishInCartItemView

        fun bind(dish: Dish?) {
            cartItemView.setData(dish)

            cartItemView.setOnClickListener {
                if (dish != null) {
                    itemClickListener?.itemClick(adapterPosition, dish)
                }
            }

            val addToCartClickListener: AddToCartClickListener = object :
                AddToCartClickListener {
                override fun addToCart(item: Dish?) {}
                override fun updateAmount(item: Dish?, amount: Int) {
                    if (item != null) {
                        itemClickListener?.updateItemAmount(item, amount)
                    }
                }
            }
            cartItemView.setAddToCartListener(addToCartClickListener)
        }
    }

    interface ItemClick {
        fun itemClick(position: Int, item: Dish)
        fun updateItemAmount(item: Dish?, amount: Int)
    }
}