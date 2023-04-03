package com.example.quotable.data

import com.example.quotable.data.response.response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/quotes")
    suspend fun getQuotes(
        @Query("page") page:String
    ):response

}