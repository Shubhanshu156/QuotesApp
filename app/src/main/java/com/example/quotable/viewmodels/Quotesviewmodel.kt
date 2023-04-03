package com.example.quotable.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotable.Resource
import com.example.quotable.data.repository
import com.example.quotable.data.response.response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinApplication.Companion.init


class Quotesviewmodel (private val repository: repository) : ViewModel() {
    var page = mutableStateOf(1)
    var isfirst= mutableStateOf(true)
    var isLoading= mutableStateOf(false)
    private var _quotelist = mutableStateListOf<com.example.quotable.data.response.Result>()

    var quotelist = _quotelist
    var position= mutableStateOf(0)

    fun getquotelist() {
        if (position.value >= 17 || isfirst.value) {
            isLoading.value = true
            if (!isfirst.value) {
                page.value += 1
            }

            viewModelScope.launch {
                when (val result = repository.getQuotes(page.value.toString())) {
                    is Resource.Success -> {
                        Log.d("list size", "${_quotelist.size}")

                        if (isfirst.value) {

                            result.data?.let { _quotelist.addAll(it.results) }
                            isfirst.value = false
                        } else {
                            result.data?.let { _quotelist.addAll(it.results) }
                        }
                        isLoading.value = false
                        position.value +=1

                    }
                    is Resource.Error -> {
                        Log.d("list size fail", "${_quotelist.size}")

                        isLoading.value = false
                        // Handle error
                    }
                    else -> {}
                }
            }
        }
        else {
            position.value += 1
        }
    }

    init {
        getquotelist()

    }



}