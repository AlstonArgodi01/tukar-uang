package com.example.tukaruangcompose.presentasion.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tukaruang2.model.repository.CurrencyRepository
import com.example.tukaruang2.util.ResourcesResponse
import com.example.tukaruangcompose.model.currency.RateCurrency
import kotlinx.coroutines.launch

class MainViewModel(
    private val currencyRepository: CurrencyRepository
    ): ViewModel() {

    fun conversion(amountFrom : Int,from : String,to : String,amountTo : Int){
        val fromAmount = amountFrom.toFloat()

        viewModelScope.launch{
            when(val getCurrency = currencyRepository.getrates(from)){
                is ResourcesResponse.Success ->{
//                    val rates = getCurrency.data!!
//                    val rateResult = RateCurrency.getRateForCurrency(
//                        to,
//                        getCurrency.data.rates
//                    )
                    Log.d("mainviewmodel", getCurrency.data?.rates.toString())
                }
                is ResourcesResponse.Error ->{
                    Log.d("mainviewmodel","error")
                }
                else -> {}
            }
        }
    }
}