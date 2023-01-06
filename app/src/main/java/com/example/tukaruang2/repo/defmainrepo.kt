package com.example.tukaruang2.repo

import com.example.tukaruang2.model.APIattri
import com.example.tukaruang2.model.CurrencyService
import com.example.tukaruang2.util.resources
import java.lang.Exception
import javax.inject.Inject

//meberikan fungsi dagger hilt
class defmainrepo @Inject constructor(private val api: CurrencyService): Mainrepo {
    override suspend fun getrates(base: String): resources<APIattri> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null){
                resources.success(result)
            }else{
                resources.error(response.message())
            }
        }catch (e: Exception){
            resources.error(e.message?:"error")
        }
    }
}