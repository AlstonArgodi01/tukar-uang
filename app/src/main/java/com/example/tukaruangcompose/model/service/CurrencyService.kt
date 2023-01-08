package com.example.tukaruang2.model.service

import com.example.tukaruang2.model.currency.CurrencyAttribute
import com.example.tukaruangcompose.model.currency.CurrencyReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("/live?access_key=b26d7cd84ef55a005c4208c1ad7f6fd9&format=1")
    suspend fun getRates(
        @Query("base") base: String
    ): retrofit2.Response<CurrencyReponse>
}