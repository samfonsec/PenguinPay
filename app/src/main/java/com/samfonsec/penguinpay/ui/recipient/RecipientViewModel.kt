package com.samfonsec.penguinpay.ui.recipient

import androidx.lifecycle.ViewModel
import com.samfonsec.penguinpay.data.model.Recipient
import com.samfonsec.penguinpay.utils.Country

class RecipientViewModel : ViewModel() {

    val recipient = RecipientObservable()
        get() {
            return field.apply {
                isValid.set(this.isValidRecipient())
            }
        }

    fun getRecipientData() = recipient.toRecipient()

    private fun RecipientObservable.toRecipient() = Recipient(
        name = "$firstName $lastName".trim(),
        country = Country.getByName(country)
    )

    private fun RecipientObservable.isValidRecipient() = with(this) {
        firstName.isNotEmpty() && lastName.isNotEmpty() && country.isNotEmpty() &&
                phone.isValidPhone(Country.getByName(country))
    }

    private fun String.isValidPhone(country: Country?) = length == country?.phoneLength

}