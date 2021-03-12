package com.example.tukaruang.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tukaruang.data.models.Rates
import com.example.tukaruang.util.dispatcherprovider
import com.example.tukaruang.util.resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

class mainviewmodel @ViewModelInject constructor(
    private val repository : mainrepo,
    private val dispatcher : dispatcherprovider
    ): ViewModel() {
        sealed class currencyevent{
            class succes(val resulttext: String): currencyevent()
            class failure(val errortext: String): currencyevent()
            object loading: currencyevent()
            object empty: currencyevent()
        }
    private val _konversi = MutableStateFlow<currencyevent>(currencyevent.empty)
    val konversion: StateFlow<currencyevent> = _konversi

    fun convert(amountStr: String,
                fromcurrency: String,
                toCurrency: String){
        val fromAmount = amountStr.toFloatOrNull()
        if (fromAmount == null){
            _konversi.value = currencyevent.failure("tidak valid")
            return
        }

        viewModelScope.launch (dispatcher.io){
            _konversi.value = currencyevent.loading
            when(val ratesreponse = repository.getrates(fromcurrency)) {
                is resource.error -> _konversi.value = currencyevent.failure(ratesreponse.massage!!)
                is resource.success -> {
                    val rates = ratesreponse.data!!.rates
                    val rate = getRateForCurrency(toCurrency, rates)
                    if (rate == null){
                        _konversi.value = currencyevent.failure("error")
                    }else{
                        val convertedcurrency = round(fromAmount * rate * 100) / 100
                        _konversi.value = currencyevent.succes(
                            "$fromAmount $fromcurrency = $convertedcurrency $toCurrency"
                        )
                    }
            }
        }
    }
}

private fun getRateForCurrency(currency: String, rates: Rates) = when (currency) {
    "CAD" -> rates.cAD
    "HKD" -> rates.hKD
    "ISK" -> rates.iSK
    "EUR" -> rates.eUR
    "PHP" -> rates.pHP
    "DKK" -> rates.dKK
    "HUF" -> rates.hUF
    "CZK" -> rates.cZK
    "AUD" -> rates.aUD
    "RON" -> rates.rON
    "SEK" -> rates.sEK
    "IDR" -> rates.iDR
    "INR" -> rates.iNR
    "BRL" -> rates.bRL
    "RUB" -> rates.rUB
    "HRK" -> rates.hRK
    "JPY" -> rates.jPY
    "THB" -> rates.tHB
    "CHF" -> rates.cHF
    "SGD" -> rates.sGD
    "PLN" -> rates.pLN
    "BGN" -> rates.bGN
    "CNY" -> rates.cNY
    "NOK" -> rates.nOK
    "NZD" -> rates.nZD
    "ZAR" -> rates.zAR
    "USD" -> rates.uSD
    "MXN" -> rates.mXN
    "ILS" -> rates.iLS
    "GBP" -> rates.gBP
    "KRW" -> rates.kRW
    "MYR" -> rates.mYR
    else -> null
}
}
