package com.example.tukaruang2.model.repository

import com.example.tukaruang2.model.currency.CurrencyAttribute
import com.example.tukaruang2.util.ResourcesResponse

interface ICurrencyRepository {
    suspend fun getrates(base : String): ResourcesResponse<CurrencyAttribute>
}