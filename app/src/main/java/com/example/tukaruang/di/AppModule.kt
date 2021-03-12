package com.example.tukaruang.di

import com.example.tukaruang.konversiApi
import com.example.tukaruang.main.defaultmainrepo
import com.example.tukaruang.main.mainrepo
import com.example.tukaruang.util.dispatcherprovider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.exchangeratesapi.io/"

//modul api
@Module
@InstallIn(ApplicationComponent::class)
object appmodule {

    @Singleton
    @Provides
    fun providecurrencyapi(): konversiApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(konversiApi::class.java)

    @Singleton
    @Provides
    fun providesmainrepository(api: konversiApi): mainrepo = defaultmainrepo(api)

    @Singleton
    @Provides   //dispatch api
    fun providedispatcher(): dispatcherprovider = object : dispatcherprovider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}