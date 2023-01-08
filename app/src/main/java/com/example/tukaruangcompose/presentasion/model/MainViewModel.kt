package com.example.tukaruangcompose.presentasion.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tukaruang2.model.repository.CurrencyRepository
import com.example.tukaruang2.util.ResourcesResponse
import com.example.tukaruangcompose.model.currency.RateCurrency
import kotlinx.coroutines.launch

class MainViewModel(val currencyRepository: CurrencyRepository): ViewModel() {

    fun conversion(amount : Int,from : String,to : String){
        val fromAmount = amount.toFloat()

        viewModelScope.launch{
            val getCurrency = currencyRepository.getrates(from)
            when(getCurrency){
                is ResourcesResponse.Success ->{
                    val rates = getCurrency.data!!.CurrencyNote
                    val rateResult = RateCurrency.getRateForCurrency(
                        to,
                        rates
                    )
                }
            }
        }
    }
}