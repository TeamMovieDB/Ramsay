package com.example.ramsay.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ramsay.model.Restaurant
import com.example.ramsay.ui.RestaurantsAdapter
import com.google.android.material.appbar.CollapsingToolbarLayout

class RestaurantFragment : Fragment(), RestaurantsAdapter.RestaurantItemClick {

    private lateinit var toolbar: CollapsingToolbarLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantList: MutableList<Restaurant>
    private lateinit var layoutManager: LinearLayoutManager
    private val recyclerViewAdapter: RestaurantsAdapter by lazy {
        RestaurantsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        settingData()
        setAdapter()
        setToolbarSettings()
    }

    override fun openDetails(position: Int, item: View?) {
        Toast.makeText(context, "ItemClicked", Toast.LENGTH_SHORT).show()
    }

    private fun setAdapter() {
        recyclerViewAdapter.setItems(restaurantList)
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun bindViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    private fun setToolbarSettings() {
        toolbar.setCollapsedTitleTextAppearance(R.style.collapsedToolbarStyle)
        toolbar.setExpandedTitleTextAppearance(R.style.expandedToolbarStyle)
        toolbar.collapsedTitleGravity = Gravity.CENTER_VERTICAL
        toolbar.expandedTitleGravity = Gravity.TOP
        toolbar.setExpandedTitleMargin(280, 0, 0, 280)
    }

    private fun settingData() {
        restaurantList = mutableListOf()
        val restaurant =
            Restaurant(12, "Bahandi", "lol", "lol", "+77077881506", "lol", "98%", "25min", "1000T")
        restaurantList.add(restaurant)
        restaurantList.add(restaurant)
        restaurantList.add(restaurant)
        restaurantList.add(restaurant)
        restaurantList.add(restaurant)
        restaurantList.add(restaurant)
        restaurantList.add(restaurant)
        restaurantList.add(restaurant)
        restaurantList.add(restaurant)
    }
}