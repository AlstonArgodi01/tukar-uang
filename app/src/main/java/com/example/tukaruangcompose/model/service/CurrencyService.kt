package com.example.tukaruang2.model.service

import com.example.tukaruang2.model.currency.CurrencyAttribute
import com.example.tukaruangcompose.model.currency.CurrencyReponse
import com.example.tukaruangcompose.model.currency.response.CurrResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("/live")
    suspend fun getRates(
        @Query("base") base: String,
        @Query("access_key") apikey: String = "b26d7cd84ef55a005c4208c1ad7f6fd9"
    ): CurrResponse
}