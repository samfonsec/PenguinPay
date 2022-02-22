package com.samfonsec.penguinpay.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val APP_ID = "282abf324acc43cdb602a9cf31faca41"

    private const val BASE_URL = "https://openexchangerates.org/api/"

    val api: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

}