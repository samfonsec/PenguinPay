package com.samfonsec.penguinpay.data.api

import com.samfonsec.penguinpay.data.model.ExchangeRate
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(LATEST_EXCHANGE_RATES_API)
    suspend fun getExchangeRates(@Query("app_id") appId: String): ExchangeRate

    companion object {
        private const val LATEST_EXCHANGE_RATES_API = "latest.json"
    }
}