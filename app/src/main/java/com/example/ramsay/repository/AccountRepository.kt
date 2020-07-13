package com.example.ramsay.repository

import com.example.ramsay.database.CustomerDao
import com.example.ramsay.model.Customer

interface AccountRepository{
    fun getCustomer(id: Int): Customer
    fun insertCustomer(customer: Customer)
}
class AccountRepositoryImpl(private val customerDao: CustomerDao): AccountRepository {
    override fun getCustomer(id: Int): Customer {
        return customerDao.getCustomer(id)
    }

    override fun insertCustomer(customer: Customer) {
        customerDao.insertCustomer(customer)
    }
}