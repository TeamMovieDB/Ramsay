package com.example.ramsay.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ramsay.model.Customer
import com.example.ramsay.model.Restaurant
import com.example.ramsay.ui.CollapsingToolbarBottom
import com.example.ramsay.ui.RestaurantsAdapter
import com.example.ramsay.utils.AppBarStateChangedListener
import com.example.ramsay.utils.State
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class RestaurantFragment : Fragment(), RestaurantsAdapter.RestaurantItemClick {

    private lateinit var toolbar: CollapsingToolbarLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantList: MutableList<Restaurant>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var collapsingToolbarBottom: CollapsingToolbarBottom
    private lateinit var appBarLayout: AppBarLayout
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
        settingFakeData()
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
        appBarLayout = view.findViewById(R.id.appbarLayout)
        collapsingToolbarBottom = view.findViewById(R.id.collapsingToolbarBottom)
        appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangedListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state == State.EXPANDED) {
                    collapsingToolbarBottom.alpha = 1F
                } else if (state == State.IDLE) {
                    collapsingToolbarBottom.alpha = 0F
                }
            }
        })
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    private fun setToolbarSettings() {
        toolbar.setCollapsedTitleTextAppearance(R.style.collapsedToolbarStyle)
        toolbar.setExpandedTitleTextAppearance(R.style.expandedToolbarStyle)
        toolbar.title = context?.getString(R.string.app_name)
        toolbar.collapsedTitleGravity = Gravity.CENTER_VERTICAL
        toolbar.expandedTitleGravity = Gravity.TOP
        toolbar.setExpandedTitleMargin(280, 0, 0, 280)
    }

    private fun settingFakeData() {
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

        val customer = Customer(
            "Alikhan Baisholanov",
            "Alikhan",
            "Baisholanov",
            "+77077881506",
            "uhuput07@gmail.com",
            "lol",
            "lol"
        )
        collapsingToolbarBottom.setUserHalfData(customer)
    }
}