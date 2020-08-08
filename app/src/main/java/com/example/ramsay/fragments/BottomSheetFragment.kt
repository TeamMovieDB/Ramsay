package com.example.ramsay.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.ramsay.R
import com.example.ramsay.utils.SharedPreferencesConfig
import com.example.ramsay.view_model.RestaurantViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject
import org.w3c.dom.Text
import java.io.Serializable
import java.util.ArrayList

class BottomSheetFragment(private val bottomSheetContext: Context?) : BottomSheetDialogFragment() {

    private lateinit var tags: MutableList<TextView>
    private lateinit var sharedPreferencesConfig: SharedPreferencesConfig
    private val restaurantViewModel: RestaurantViewModel by inject()
    private lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tags = arrayListOf()
        bundle = Bundle()
        sharedPreferencesConfig = SharedPreferencesConfig(bottomSheetContext)

        val serializable = bundle?.getSerializable("tags")
        Log.d("serializable_check", serializable.toString())
        Log.d("tags_extr_count", tags.size.toString())
    }

    private fun getTags(bundle: Bundle?){
        restaurantViewModel.liveData.observe(activity, Observer { result ->
            when (result) {
                is RestaurantViewModel.State.DBfilled -> {
                    restaurantViewModel.getRestaurants()
                }
                is RestaurantViewModel.State.RestaurantList -> {

                }
                is RestaurantViewModel.State.HideLoading -> {
                }
            }
        })
    }

}