package com.samfonsec.penguinpay.ui.recipient

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import com.samfonsec.penguinpay.BR

import kotlin.properties.Delegates

class RecipientObservable : BaseObservable() {

    @get:Bindable
    var firstName: String by Delegates.observable("") { _, _, _ ->
        notifyPropertyChanged(BR.firstName)
    }

    @get:Bindable
    var lastName: String by Delegates.observable("") { _, _, _ ->
        notifyPropertyChanged(BR.lastName)
    }

    @get:Bindable
    var country: String by Delegates.observable("") { _, _, _ ->
        notifyPropertyChanged(BR.country)
    }

    @get:Bindable
    var phone: String by Delegates.observable("") { _, _, _ ->
        notifyPropertyChanged(BR.phone)
    }

    var isValid = ObservableBoolean(false)
}