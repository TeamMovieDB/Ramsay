package com.example.ramsay.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.R
import com.example.ramsay.model.Dish
import com.example.ramsay.ui.BasketAdapter
import com.example.ramsay.view_model.BasketViewModel
import com.example.ramsay.view_model.RestaurantDetailsViewModel
import org.koin.android.ext.android.inject

class BasketFragment : Fragment(), BasketAdapter.ItemClick {

    private lateinit var basketRecyclerView: RecyclerView
    private lateinit var tvPrice: TextView
    private lateinit var btnOrder: Button

    private val basketAdapter: BasketAdapter by lazy {
        BasketAdapter(itemClickListener = this)
    }
    private val basketViewModel: BasketViewModel by inject()
    private val restaurantDetailsViewModel: RestaurantDetailsViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.basket_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setAdapter()
        getBasketItems()
        observe()
    }

    override fun itemClick(position: Int, item: Dish) {
        Toast.makeText(requireContext(), "hello", Toast.LENGTH_SHORT).show()
    }

    override fun updateItemAmount(item: Dish?, amount: Int) {
        basketViewModel.changeOrderedDishAmount(item, amount)
        // observe()
    }

    private fun bindViews(view: View) {
        basketRecyclerView = view.findViewById(R.id.basketRecyclerView)
        tvPrice = view.findViewById(R.id.tvPrice)
        btnOrder = view.findViewById(R.id.btnOrder)

        btnOrder.setOnClickListener {
            Toast.makeText(requireContext(), "Order made", Toast.LENGTH_SHORT).show()
            basketViewModel.clearBasket()
        }
    }

    private fun setAdapter() {
        basketRecyclerView.layoutManager = LinearLayoutManager(context)
        basketRecyclerView.adapter = basketAdapter
    }

    private fun getBasketItems() {
        basketViewModel.getItems()
        // observe()
    }

    private fun getTotalPrice(items: List<Dish>?): Int {
        var count = 0
        if (!items.isNullOrEmpty()) {
            for (item in items) {
                count += item.amount?.let { item.price?.times(it) } ?: 0
            }
        }
        return count
    }

    private fun observe() {
        basketViewModel.liveData.observe(requireActivity(), Observer { result ->
            when (result) {
                is BasketViewModel.State.Items -> {
                    basketAdapter.setCartItems(result.items)
                    tvPrice.text = context?.getString(R.string.price, getTotalPrice(result.items))
                }
                is BasketViewModel.State.Inserted -> {
                    basketAdapter.insertItem(result.item)
                }
                is BasketViewModel.State.BasketCleared -> {
                    basketAdapter.removeItems()
                    restaurantDetailsViewModel.itemsDeleted()
                }
            }
        })
    }
}
