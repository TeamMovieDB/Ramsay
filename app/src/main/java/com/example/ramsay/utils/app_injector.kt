package com.example.ramsay.utils

import android.content.Context
import com.example.ramsay.database.RestaurantDao
import com.example.ramsay.database.RestaurantDatabase
import com.example.ramsay.repository.RestaurantRepository
import com.example.ramsay.repository.RestaurantRepositoryImpl
import com.example.ramsay.view_model.BasketViewModel
import com.example.ramsay.view_model.RestaurantDetailsViewModel
import com.example.ramsay.view_model.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val storageModule = module {
    single { getRestaurantDao(context = get()) }
}

val repositoryModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl(restaurantDao = get()) }
}

val viewModelModule1 = module {
    viewModel { RestaurantDetailsViewModel(restaurantRepository = get()) }
    viewModel { RestaurantViewModel(restaurantRepository = get()) }
    viewModel { BasketViewModel(restaurantRepository = get()) }
}


val appModule = listOf(storageModule, repositoryModule, viewModelModule1)

private fun getRestaurantDao(context: Context): RestaurantDao {
    return RestaurantDatabase.getDatabase(context).restaurantDao()
}