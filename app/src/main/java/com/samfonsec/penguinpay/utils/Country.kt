package com.samfonsec.penguinpay.utils

enum class Country(val currency: String, val phonePrefix: String, val phoneLength: Int) {
    Kenya("KES", "+254", 9),
    Nigeria("NGN", "+254", 7),
    Tanzania("TZS", "+255", 9),
    Uganda("UGX", "+256", 7);

    companion object {
        fun names() = values().map { it.name }

        fun getByName(name: String) = values().firstOrNull { it.name == name }
    }
}

