package com.samfonsec.penguinpay.ui.sendMoney

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samfonsec.penguinpay.data.repository.RatesRepository
import com.samfonsec.penguinpay.utils.Country
import kotlinx.coroutines.launch
import kotlin.math.roundToLong

class SendMoneyViewModel(
    private val ratesRepository: RatesRepository
) : ViewModel() {

    private val _onResult = MutableLiveData<Double>()
    val onResult: LiveData<Double> = _onResult

    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading: LiveData<Boolean> = _onLoading

    private val _onError = MutableLiveData<Boolean>()
    val onError: LiveData<Boolean> = _onError

    private var exchangeRate = 0.0

    var country: Country? = null

    val sendMoney = SendMoneyObservable()
        get() = field.apply {
            receive = send.totalValueInBinary()
            isValid.set(receive != ZERO)
        }

    fun getExchangeRate() {
        _onLoading.postValue(true)
        viewModelScope.launch {
            ratesRepository.getExchangeRates().getOrNull()?.let {
                exchangeRate = it.getRateByCountry(country)
                _onResult.postValue(exchangeRate)
            } ?: _onError.postValue(true)

            _onLoading.postValue(false)
        }
    }

    fun isLongMaxValueReached() = sendMoney.receive.fromBinary() == Long.MAX_VALUE

    private fun String.totalValueInBinary(): String {
        return if (this.isEmpty())
            ZERO
        else {
            (fromBinary() * exchangeRate).toBinary()
        }
    }

    private fun String.fromBinary() = toLong(BINARY_RADIX)

    private fun Double.toBinary() = roundToLong().toString(BINARY_RADIX)

    companion object {
        private const val ZERO = "0"
        private const val BINARY_RADIX = 2
    }
}