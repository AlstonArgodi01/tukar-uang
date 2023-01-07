package com.example.tukaruang2.module

import android.content.Context
import com.example.tukaruang2.model.repository.CurrencyRepository
import com.example.tukaruangcompose.module.ApiConfig

object Injection {
    fun provideCurrencyRepository(context : Context): CurrencyRepository{
        return  CurrencyRepository(ApiConfig.setCurrencyService())
    }
}
