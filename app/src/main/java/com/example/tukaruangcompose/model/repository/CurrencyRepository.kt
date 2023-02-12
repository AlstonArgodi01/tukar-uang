package com.example.tukaruang2.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.tukaruang2.model.service.CurrencyService
import com.example.tukaruangcompose.model.currency.response.CurrResponse
import com.example.tukaruangcompose.model.utils.ResultRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//meberikan fungsi dagger hilt
class CurrencyRepository(
    private val api: CurrencyService
):ICurrencyRepository {
    override suspend fun getRates(base: String): LiveData<ResultRespon<CurrResponse>> =
        liveData {
            emit(ResultRespon.Loading)
            try {
                val result = api.getRates(base = base)
//                api.getRates(base).enqueue(object : Callback<CurrResponse>{
//                    override fun onResponse(
//                        call: Call<CurrResponse>,
//                        response: Response<CurrResponse>
//                    ) {
//                        Log.d("repository",response.body().toString())
////                        emit(ResultRespon.Success(response.body()))
//                    }
//
//                    override fun onFailure(call: Call<CurrResponse>, t: Throwable) {
//                       Log.d("repository","error")
//                    }
//
//                })

                emit(ResultRespon.Success(result))
            }catch (e : Exception){
                emit(ResultRespon.Error(e.message.toString()))
            }
        }

}