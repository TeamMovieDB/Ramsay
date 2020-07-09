package com.example.ramsay.repository

import com.example.ramsay.model.Dish
import com.example.ramsay.model.Restaurant

interface RestaurantRepository {
    fun getRestaurant(): Restaurant
    fun getMenu(): List<Dish>
}

class RestaurantRepositoryImpl : RestaurantRepository {
    override fun getRestaurant(): Restaurant {
        return Restaurant(
            12, "Bahandi",
            "The best restaurant with tasty burgers",
            "Almaty, Tole bi street, 50",
            "+77077881506", "lol", "98%", "25min", "1000T"
        )
    }

    override fun getMenu(): List<Dish> {
        val menu: MutableList<Dish> = mutableListOf()

        for (i in 1..20) {

            val dish = Dish(
                id = 1,
                title = "Big Burger",
                description = "A hamburger (also burger for short) is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun. The patty may be pan fried, grilled, smoked[1] or flame broiled. Hamburgers are often served with cheese, lettuce, tomato, onion, pickles, bacon, or chiles; condiments such as ketchup, mustard, mayonnaise, relish",
                price = 1200
            )
            menu.add(dish)
        }
        return menu
    }
}
