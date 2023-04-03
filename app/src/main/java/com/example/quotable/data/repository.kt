package com.example.quotable.data


import com.example.quotable.Resource
import com.example.quotable.data.response.response

class repository constructor(
    private val api:APIService
) {
    suspend fun getQuotes(page:String):Resource<response>{
        try {
            val results = api.getQuotes(page)
            return Resource.Success(results)
        } catch (e: Exception) {
            val errorMessage = "Failed to get quotes: ${e.message}"
            return Resource.Error(errorMessage)
        }
    }

}