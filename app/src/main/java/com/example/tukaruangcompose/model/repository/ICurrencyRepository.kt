package com.example.tukaruang2.model.repository

import com.example.tukaruang2.model.currency.CurrencyAttribute
import com.example.tukaruang2.util.ResourcesResponse
import com.example.tukaruangcompose.model.currency.CurrencyReponse

interface ICurrencyRepository {
    suspend fun getrates(base : String): ResourcesResponse<CurrencyReponse>
}