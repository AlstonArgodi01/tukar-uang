package com.example.tukaruang2.model.repository

import androidx.lifecycle.LiveData
import com.example.tukaruang2.util.ResourcesResponse
import com.example.tukaruangcompose.model.currency.response.CurrResponse
import com.example.tukaruangcompose.model.utils.ResultRespon

interface ICurrencyRepository {
    suspend fun getRates(base : String): LiveData<ResultRespon<CurrResponse>>
}