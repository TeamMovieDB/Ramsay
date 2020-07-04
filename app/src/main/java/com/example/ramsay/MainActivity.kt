package com.example.ramsay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ramsay.model.Restaurant
import com.example.ramsay.ui.RestaurantItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        givingFakeData()
    }

    private fun bindViews() {
    }

    private fun givingFakeData() {
    }
}