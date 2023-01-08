package com.example.tukaruang2.model.repository

import com.example.tukaruang2.model.currency.CurrencyAttribute
import com.example.tukaruang2.model.currency.CurrencyRates
import com.example.tukaruang2.model.service.CurrencyService
import com.example.tukaruang2.util.ResourcesResponse
import com.example.tukaruangcompose.model.currency.CurrencyReponse
import java.lang.Exception

//meberikan fungsi dagger hilt
class CurrencyRepository(
    private val api: CurrencyService
):ICurrencyRepository {
    override suspend fun getrates(base: String): ResourcesResponse<CurrencyReponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null){
                ResourcesResponse.Success(result)
            }else{
                ResourcesResponse.Error(response.message())
            }
        }catch (e: Exception){
            ResourcesResponse.Error(e.message?:"Error")
        }
    }
}