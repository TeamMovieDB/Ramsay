package com.example.ramsay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.example.ramsay.fragments.RestaurantFragment

class MainActivity : AppCompatActivity() {
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val restaurantsFragment = RestaurantFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        givingFakeData()
        fragmentManager.beginTransaction().add(R.id.frame, restaurantsFragment).commit()
    }

    fun bindViews() {

    }

    private fun givingFakeData() {
    }
}