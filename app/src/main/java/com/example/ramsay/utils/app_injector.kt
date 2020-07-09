package com.example.ramsay.utils

import android.content.Context
import com.example.ramsay.database.RestaurantDao
import com.example.ramsay.database.RestaurantDatabase
import com.example.ramsay.repository.RestaurantRepository
import com.example.ramsay.repository.RestaurantRepositoryImpl
import com.example.ramsay.view_model.RestaurantDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val storageModule = module {
    single { getRestaurantDao(context = get()) }
}

val repositoryModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl(restaurantDao = get()) }
}

val viewModelModule = module {
    viewModel { RestaurantDetailsViewModel(restaurantRepository = get()) }
}

val appModule = listOf(storageModule, repositoryModule, viewModelModule)

private fun getRestaurantDao(context: Context): RestaurantDao {
    return RestaurantDatabase.getDatabase(context).restaurantDao()
}