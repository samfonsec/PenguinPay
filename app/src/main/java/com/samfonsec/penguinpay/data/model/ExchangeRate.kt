package com.samfonsec.penguinpay.data.model

import com.samfonsec.penguinpay.utils.Country
import com.samfonsec.penguinpay.utils.Country.*

data class ExchangeRate(
    val rates: Rates
) {
    fun getRateByCountry(country: Country?) = when (country) {
        Kenya -> rates.KES
        Nigeria -> rates.NGN
        Tanzania -> rates.TZS
        Uganda -> rates.UGX
        else -> 0.0
    }
}