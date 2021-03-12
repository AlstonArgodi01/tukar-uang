package com.example.tukaruang

import com.example.tukaruang.data.models.konversirespon
import retrofit2.http.GET
import retrofit2.http.Query

//untuk mengambil data dari api
interface konversiApi {

    @GET("/latest")
    suspend fun getRates( //ambil curency
            @Query("base") base: String //mengambil query
    ): retrofit2.Response<konversirespon>
}