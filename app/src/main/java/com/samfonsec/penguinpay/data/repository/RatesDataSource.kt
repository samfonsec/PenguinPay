package com.samfonsec.penguinpay.data.repository

import com.samfonsec.penguinpay.data.api.ApiClient.APP_ID
import com.samfonsec.penguinpay.data.api.ApiClient.api
import com.samfonsec.penguinpay.data.model.ExchangeRate

class RatesDataSource : RatesRepository {

    override suspend fun getExchangeRates(): Result<ExchangeRate> {
        return try {
            Result.success(api.getExchangeRates(APP_ID))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}