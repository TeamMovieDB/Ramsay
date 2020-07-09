package com.example.ramsay.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.R
import com.example.ramsay.model.Dish
import com.example.ramsay.ui.MenuAdapter
import com.example.ramsay.utils.AppBarStateChangedListener
import com.example.ramsay.utils.BUNDLE_KEY
import com.example.ramsay.utils.State
import com.example.ramsay.view_model.RestaurantDetailsViewModel
import com.example.ramsay.widgets.RestaurantDetailsView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.android.ext.android.inject

class RestaurantDetailsFragment : Fragment(),
    MenuAdapter.RecyclerViewItemClick {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appbarLayout: AppBarLayout
    private lateinit var restDescriptionCard: RestaurantDetailsView
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var progressBar: ProgressBar
    private var restaurantId = 1

    private val menuAdapter: MenuAdapter by lazy {
        MenuAdapter(itemClickListener = this)
    }

    private val restaurantDetailsViewModel: RestaurantDetailsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        getBundle()
        setListeners()
        setAdapter()
        observe()
    }

    override fun itemClick(position: Int, item: Dish) {
        Toast.makeText(requireContext(), "hello", Toast.LENGTH_SHORT).show()
    }

    private fun bindViews(view: View) {
        recyclerView = view.findViewById(R.id.menuRecyclerView)
        appbarLayout = view.findViewById(R.id.appbarLayout)
        restDescriptionCard = view.findViewById(R.id.restDescriptionCard)
        collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbarLayout)
        progressBar = view.findViewById(R.id.progressBar)
    }

    private fun getBundle() {
        val bundle = this.arguments
        if (bundle != null) {
            restaurantId = bundle.getInt(BUNDLE_KEY)
        }
    }

    private fun setListeners() {

        appbarLayout.addOnOffsetChangedListener(object : AppBarStateChangedListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state == State.EXPANDED) {
                    restDescriptionCard.alpha = 1F
                    collapsingToolbarLayout.setBackgroundColor(resources.getColor(R.color.white))
                } else {
                    restDescriptionCard.alpha = 0F
                    collapsingToolbarLayout.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                }
            }
        })
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = menuAdapter
    }

    private fun observe() {
        restaurantDetailsViewModel.liveData.observe(requireActivity(), Observer { result ->
            when (result) {

                is RestaurantDetailsViewModel.State.DatabaseFilled -> {
                    restaurantDetailsViewModel.getRestaurantDescription(restaurantId)
                    restaurantDetailsViewModel.getMenu()
                }
                is RestaurantDetailsViewModel.State.Menu -> {
                    menuAdapter.setMenu(result.menu)
                    menuAdapter.notifyDataSetChanged()
                }
                is RestaurantDetailsViewModel.State.RestaurantDetails -> {
                    result.restaurant?.let { restDescriptionCard.setData(it) }
                }
                is RestaurantDetailsViewModel.State.HideLoading -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }
}
