package com.example.ramsay.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.ramsay.R
import com.example.ramsay.model.Customer
import com.example.ramsay.model.Restaurant
import com.example.ramsay.ui.CollapsingToolbarBottom
import com.example.ramsay.ui.RestaurantsAdapter
import com.example.ramsay.utils.AppBarStateChangedListener
import com.example.ramsay.utils.BUNDLE_KEY
import com.example.ramsay.utils.State
import com.example.ramsay.view_model.RestaurantViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject
import java.io.Serializable

class RestaurantFragment : Fragment(), RestaurantsAdapter.RestaurantItemClick {

    private lateinit var toolbar: CollapsingToolbarLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantList: MutableList<Restaurant>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var collapsingToolbarBottom: CollapsingToolbarBottom
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var floatingButton: FloatingActionButton
    private lateinit var progressBar: ProgressBar


    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_DRAGGING -> floatingButton.hide()
                RecyclerView.SCROLL_STATE_IDLE -> {
                    val firstVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (firstVisibleItem <= 5) {
                        floatingButton.hide()
                    } else {
                        floatingButton.show()
                    }
                }
            }
        }
    }
    private val restaurantViewModel: RestaurantViewModel by inject()
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
        getRestaurants()
        setAdapter()
        setToolbarSettings()
        setFakeUserData()
    }

    override fun onResume() {
        super.onResume()
        recyclerView.addOnScrollListener(scrollListener)
    }

    override fun onPause() {
        super.onPause()
        recyclerView.removeOnScrollListener(scrollListener)
    }

    override fun openDetails(position: Int, item: Restaurant?) {
        val bundle = Bundle()
        item?.id?.let { bundle.putInt(BUNDLE_KEY, it) }
        val restaurantMenuFragment = RestaurantDetailsFragment()
        restaurantMenuFragment.arguments = bundle
        fragmentManager?.beginTransaction()?.add(R.id.frame, restaurantMenuFragment)
            ?.addToBackStack(null)?.commit()
    }


    private fun setAdapter() {
        recyclerViewAdapter.setItems(restaurantList)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun bindViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recyclerView)
        appBarLayout = view.findViewById(R.id.appbarLayout)
        collapsingToolbarBottom = view.findViewById(R.id.collapsingToolbarBottom)
        floatingButton = view.findViewById(R.id.floatingActionButton)
        progressBar = view.findViewById(R.id.restaurantsProgressBar)

        appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangedListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state == State.EXPANDED) {
                    collapsingToolbarBottom.alpha = 1F
                } else if (state == State.IDLE) {
                    collapsingToolbarBottom.alpha = 0F
                }
            }
        })
        floatingButton.setOnClickListener {
            scrollingToTop()
        }
        collapsingToolbarBottom.ivAvatar.setOnClickListener {
            callBottomSheetFragment()
        }
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        restaurantList = mutableListOf()

    }

    private fun getRestaurants() {
        restaurantViewModel.liveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is RestaurantViewModel.State.DBfilled -> {
                    restaurantViewModel.getRestaurants()
                }
                is RestaurantViewModel.State.RestaurantList -> {
                    result.restaurantResult?.let {
                        restaurantList.addAll(it)
                        //  listOfTags.addAll(setTagsList(restaurantList))
                    }
                    recyclerViewAdapter.notifyDataSetChanged()
                }
                is RestaurantViewModel.State.HideLoading -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }


    private fun setToolbarSettings() {
        toolbar.setCollapsedTitleTextAppearance(R.style.collapsedToolbarStyle)
        toolbar.setExpandedTitleTextAppearance(R.style.expandedToolbarStyle)
        toolbar.title = context?.getString(R.string.app_name)
        toolbar.collapsedTitleGravity = Gravity.CENTER_VERTICAL
        toolbar.expandedTitleMarginTop = 0
    }

    private fun scrollingToTop() {
        val smoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_ANY
            }
        }
        smoothScroller.targetPosition = 0
        layoutManager.startSmoothScroll(smoothScroller)
    }

    private fun setFakeUserData() {
        val customer = Customer(
            12,
            "Alikhan Baisholanov",
            "Alikhan",
            "Baisholanov",
            "+77077881506",
            "uhuput07@gmail.com",
            "lol",
            "lol",
            "password"
        )
        collapsingToolbarBottom.setUserHalfData(customer)
    }

    private fun callBottomSheetFragment() {
        //set list of tags
        //set bundle
        //open fragment
        val listOfTags: MutableList<String> = mutableListOf()
        for (restaurant in restaurantList) {
            listOfTags.add(restaurant.name)
        }
        val bundle = Bundle()
        bundle.putSerializable("tags", listOfTags as Serializable)
        val bottomSheetFragment = BottomSheetFragment(context)
        bottomSheetFragment.arguments = bundle

        bottomSheetFragment.show(requireActivity().supportFragmentManager, "BottomSheet")
    }
}
