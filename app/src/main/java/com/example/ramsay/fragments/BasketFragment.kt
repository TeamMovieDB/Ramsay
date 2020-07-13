package com.example.ramsay.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.R
import com.example.ramsay.model.Dish
import com.example.ramsay.ui.BasketAdapter
import com.example.ramsay.view_model.BasketViewModel
import org.koin.android.ext.android.inject

class BasketFragment : Fragment(), BasketAdapter.ItemClick {

    private lateinit var basketRecyclerView: RecyclerView
    private val basketAdapter: BasketAdapter by lazy {
        BasketAdapter(itemClickListener = this)
    }
    private val basketViewModel: BasketViewModel by inject()

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
    }

    override fun itemClick(position: Int, item: Dish) {
        Toast.makeText(requireContext(), "hello", Toast.LENGTH_SHORT).show()
    }

    private fun bindViews(view: View) {
        basketRecyclerView = view.findViewById(R.id.basketRecyclerView)
    }

    private fun setAdapter() {
        basketRecyclerView.layoutManager = LinearLayoutManager(context)
        basketRecyclerView.adapter = basketAdapter
    }

    private fun getBasketItems() {
        basketViewModel.getItems()
        basketViewModel.liveData.observe(requireActivity(), Observer { result ->
            when (result) {
                is BasketViewModel.State.Items -> {
                    basketAdapter.setCartItems(result.items)
                }
                is BasketViewModel.State.Inserted -> {
                    basketAdapter.insertItem(result.item)
                }
            }
        })
    }
}
