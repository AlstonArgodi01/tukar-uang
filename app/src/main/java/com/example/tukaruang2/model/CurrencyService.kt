package com.example.tukaruang2.model

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("/live?access_key=b26d7cd84ef55a005c4208c1ad7f6fd9&format=1")
    suspend fun getRates(@Query("base") base: String): retrofit2.Response<APIattri>
}