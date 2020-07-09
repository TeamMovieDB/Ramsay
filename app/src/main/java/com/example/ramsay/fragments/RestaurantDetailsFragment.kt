package com.example.ramsay.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.R
import com.example.ramsay.model.Dish
import com.example.ramsay.model.Restaurant
import com.example.ramsay.ui.MenuAdapter
import com.example.ramsay.widgets.RestaurantDetailsView
import com.example.ramsay.utils.AppBarStateChangedListener
import com.example.ramsay.utils.State
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout


class RestaurantDetailsFragment : Fragment(),
    MenuAdapter.RecyclerViewItemClick {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appbarLayout: AppBarLayout
    private lateinit var restDescriptionCard: RestaurantDetailsView
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    private val menuAdapter: MenuAdapter by lazy {
        MenuAdapter(itemClickListener = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews(view)
        setListeners()
        setAdapter()
        val menu = getMenu()
        menuAdapter.setMenu(menu)
        setRestaurantData()
    }

    override fun itemClick(position: Int, item: Dish) {
        Toast.makeText(requireContext(), "hello", Toast.LENGTH_SHORT).show()
    }

    private fun bindViews(view: View) {
        recyclerView = view.findViewById(R.id.menuRecyclerView)
        appbarLayout = view.findViewById(R.id.appbarLayout)
        restDescriptionCard = view.findViewById(R.id.restDescriptionCard)
        collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbarLayout)
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

    private fun getMenu(): List<Dish> {
        val menu: MutableList<Dish> = mutableListOf()

        for (i in 1..20) {

            val dish = Dish(
                id = 1,
                title = "Hunter x Hunter",
                description = "A Hunter is one who travels the world doing all sorts of dangerous tasks. From capturing criminals to searching deep within uncharted lands for any lost treasures. Gon is a young boy whose father disappeared long ago, being a Hunter. He believes if he could also follow his father's path, he could one day reunite with him.",
                price = 1200
            )
            menu.add(dish)
        }
        return menu
    }

    private fun setRestaurantData() {
        val restaurant =
            Restaurant(
                12, "Bahandi",
                "A Hunter is one who travels the world doing all sorts of dangerous tasks. ",
                "Almaty, Tole bi street, 50",
                "+77077881506", "lol", "98%", "25min", "1000T"
            )
        restDescriptionCard.setData(restaurant)
    }
}