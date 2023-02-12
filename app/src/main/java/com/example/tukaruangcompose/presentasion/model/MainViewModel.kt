package com.example.tukaruangcompose.presentasion.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tukaruang2.model.repository.CurrencyRepository
import com.example.tukaruang2.util.ResourcesResponse
import com.example.tukaruangcompose.model.currency.response.CurrResponse
import com.example.tukaruangcompose.model.utils.ResultRespon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val currencyRepository: CurrencyRepository
): ViewModel() {

    suspend fun conversion(amountFrom : Int, from : String, to : String, amountTo : Int)
    : LiveData<ResultRespon<CurrResponse>> {
        val result = currencyRepository.getRates(base = from)
        return result
    }
}