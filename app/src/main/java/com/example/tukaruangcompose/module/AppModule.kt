package com.example.tukaruang2.module

import com.example.tukaruang2.model.service.CurrencyService
import com.example.tukaruang2.model.repository.ICurrencyRepository
import com.example.tukaruang2.model.repository.CurrencyRepository
import com.example.tukaruang2.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val base_url = "http://api.currencylayer.com/"

@Module
@InstallIn(ApplicationComponentManager::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCurrencyService(): CurrencyService = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create()) //karena file gson
        .build()
        .create(CurrencyService::class.java)

    //main repo
    @Singleton
    @Provides
    fun provideMainRepository(api : CurrencyService): ICurrencyRepository = CurrencyRepository(api)

    @Singleton
    @Provides
    fun provideDispatcher(): DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfied: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}