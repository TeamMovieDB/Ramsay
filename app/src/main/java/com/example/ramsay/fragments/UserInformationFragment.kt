package com.example.ramsay.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ramsay.R
import com.example.ramsay.model.Customer
import com.example.ramsay.view_model.CustomerViewModel
import com.example.ramsay.view_model.RestaurantViewModel
import org.koin.android.ext.android.inject

class UserInformationFragment : Fragment() {
    private lateinit var etNameSurname: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPassword: EditText
    private var customer: Customer = Customer(
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
    private lateinit var llUserData: LinearLayout
    private val customerViewModel: CustomerViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_information_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setData(customer)
    }

    private fun bindViews(view: View) {
        etNameSurname = view.findViewById(R.id.etNameSurname)
        etPhone = view.findViewById(R.id.etPhone)
        etEmail = view.findViewById(R.id.etEmail)
        etAddress = view.findViewById(R.id.etAddress)
        etPassword = view.findViewById(R.id.etPassword)
        llUserData = view.findViewById(R.id.llUserData)
    }
//    private fun getCustomer(){
//        customerViewModel.liveData.observe(viewLifecycleOwner, Observer { result ->
//            when (result) {
//                is CustomerViewModel.State.DBfilled -> {
//                    customerViewModel.getCustomer(12)
//                }
//                is CustomerViewModel.State.Result -> {
//                    customer = result.customerResult
//                    Log.d("IT_CUSTOMER", result.customerResult.toString())
//                }
//                is CustomerViewModel.State.HideLoading -> {
//                    llUserData.removeView(progressBar)
//                }
//            }
//        })
//    }
    private fun setData(customer: Customer) {
        etNameSurname.setText(context?.getString(R.string.name_surname,customer.firstName, customer.lastName))
        etPhone.setText(customer.phone)
        etAddress.setText(customer.address)
        etEmail.setText(customer.email)
        etPassword.setText(customer.password)
    }
}