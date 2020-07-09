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
            "",
            "Almaty, Tole bi street, 50",
            "+77077881506", "lol", "98%", "25min", "1000T"
        )
    }

    override fun getMenu(): List<Dish> {
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
}
