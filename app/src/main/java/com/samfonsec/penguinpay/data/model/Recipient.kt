package com.samfonsec.penguinpay.data.model

import android.os.Parcelable
import com.samfonsec.penguinpay.utils.Country
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipient(
    val name: String,
    val country: Country?
) : Parcelable