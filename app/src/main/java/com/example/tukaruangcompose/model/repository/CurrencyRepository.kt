package com.example.tukaruang2.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.tukaruang2.model.service.CurrencyService
import com.example.tukaruangcompose.model.currency.response.CurrResponse
import com.example.tukaruangcompose.model.utils.ResultRespon

//meberikan fungsi dagger hilt
class CurrencyRepository(
    private val api: CurrencyService
):ICurrencyRepository {
    override suspend fun getRates(base: String): LiveData<ResultRespon<CurrResponse>> =
        liveData {
            emit(ResultRespon.Loading)
            try {
                val result = api.getRates(base = base)
                emit(ResultRespon.Success(result))
            }catch (e : Exception){
                emit(ResultRespon.Error(e.message.toString()))
            }
        }

}