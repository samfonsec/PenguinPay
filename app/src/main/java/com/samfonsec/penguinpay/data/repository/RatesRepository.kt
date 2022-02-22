package com.samfonsec.penguinpay.data.repository

import com.samfonsec.penguinpay.data.model.ExchangeRate

interface RatesRepository {

    suspend fun getExchangeRates(): Result<ExchangeRate>
}