package com.example.quotable.koin

import com.example.quotable.data.APIService
import com.example.quotable.data.repository
import com.example.quotable.viewmodels.Quotesviewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule= module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://quotable.io")
            .build()
            .create(APIService::class.java)
    }
    factory {
        repository(get())
    }
    viewModel { Quotesviewmodel(get()) }


}