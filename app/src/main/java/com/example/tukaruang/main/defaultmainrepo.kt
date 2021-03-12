package com.example.tukaruang.main

import com.example.tukaruang.data.models.konversirespon
import com.example.tukaruang.konversiApi
import com.example.tukaruang.util.resource
import java.lang.Exception
import javax.inject.Inject

//dager hilt
class defaultmainrepo @Inject constructor(private val api: konversiApi): mainrepo { //inject api
    override suspend fun getrates(base: String): resource<konversirespon> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result !=null){
                resource.success(result)
            }else{
                resource.error(response.message())
            }
        } catch (e:Exception){
            resource.error(e.message?:"error") //respon jika gagal mengambil api
        }
    }
}