package com.example.ramsay.utils

import android.content.Context
import com.example.ramsay.database.CustomerDao
import com.example.ramsay.database.CustomerDatabase
import com.example.ramsay.database.RestaurantDao
import com.example.ramsay.database.RestaurantDatabase
import com.example.ramsay.repository.AccountRepository
import com.example.ramsay.repository.AccountRepositoryImpl
import com.example.ramsay.repository.RestaurantRepository
import com.example.ramsay.repository.RestaurantRepositoryImpl
import com.example.ramsay.view_model.CustomerViewModel
import com.example.ramsay.view_model.RestaurantDetailsViewModel
import com.example.ramsay.view_model.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val storageModule1 = module {
    single { getRestaurantDao(context = get()) }
}

val storageModule2 = module {
    single { getAccountDao(context = get()) }
}
val restaurantRepositoryModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl(restaurantDao = get()) }
}
val customerRepositoryModule = module {
    single<AccountRepository> { AccountRepositoryImpl(customerDao = get()) }
}
val viewModelModule1 = module {
    viewModel { RestaurantDetailsViewModel(restaurantRepository = get()) }
}
val viewModelModule2 = module {
    viewModel { RestaurantViewModel(restaurantRepository = get()) }
}

val viewModelModule3 = module {
    viewModel { CustomerViewModel(accountRepository = get()) }
}
val appModule =
    listOf(
        storageModule1,
        storageModule2,
        restaurantRepositoryModule,
        customerRepositoryModule,
        viewModelModule1,
        viewModelModule2,
        viewModelModule3
    )

private fun getRestaurantDao(context: Context): RestaurantDao {
    return RestaurantDatabase.getDatabase(context).restaurantDao()
}

private fun getAccountDao(context: Context): CustomerDao {
    return CustomerDatabase.getDatabase(context).customerDao()
}