package com.example.tukaruangcompose.model.currency

import com.example.tukaruang2.model.currency.Rates

data class CurrencyReponse(
    val base: String,
    val date: String,
    val rates: Rates
)