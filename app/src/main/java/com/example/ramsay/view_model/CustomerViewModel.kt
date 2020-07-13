package com.example.ramsay.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ramsay.model.Customer
import com.example.ramsay.model.Restaurant
import com.example.ramsay.repository.AccountRepository
import com.example.ramsay.repository.RestaurantRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CustomerViewModel(private var accountRepository: AccountRepository) : ViewModel(),
    CoroutineScope {
    val liveData = MutableLiveData<State>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    init {
        setCustomer()
    }

    private fun setCustomer() {
        launch {
            val customer = Customer(
                12,
                "Aleeh",
                "Alikhan",
                "Baisholanov",
                "+77077881506",
                "uhuput07@gmail.com",
                "Lol",
                "lol",
                "AleehBai"
            )
            withContext(Dispatchers.IO) {
                accountRepository.insertCustomer(customer)
            }
        }
        liveData.value = State.DBfilled
    }

    fun getCustomer(id: Int) {
        launch {
            liveData.value = State.DBfilled
            val customer = withContext(Dispatchers.IO) {
                return@withContext accountRepository.getCustomer(id)
            }
            liveData.value = State.HideLoading
            liveData.value = State.Result(customer)
        }
    }

    sealed class State {
        object DBfilled : State()
        object ShowLoading : State()
        object HideLoading : State()
        data class Result(val customerResult: Customer) : State()
    }
}