package com.samfonsec.penguinpay.ui.sendMoney

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import com.samfonsec.penguinpay.BR

import kotlin.properties.Delegates

class SendMoneyObservable : BaseObservable() {

    @get:Bindable
    var send: String by Delegates.observable("") { _, _, _ ->
        notifyPropertyChanged(BR.send)
    }

    @get:Bindable
    var receive: String by Delegates.observable("") { _, _, _ ->
        notifyPropertyChanged(BR.receive)
    }

    var isValid = ObservableBoolean(false)
}