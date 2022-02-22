package com.samfonsec.penguinpay.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samfonsec.penguinpay.data.repository.RatesDataSource
import com.samfonsec.penguinpay.ui.sendMoney.SendMoneyViewModel

class SendMoneyViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendMoneyViewModel::class.java)) {
            return SendMoneyViewModel(RatesDataSource()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}